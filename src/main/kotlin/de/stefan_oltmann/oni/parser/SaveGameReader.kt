/*
 * ONI Seed Browser
 * Copyright (C) 2023 RoboPhred & Bryan Gonzalez
 * Copyright (C) 2024 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.stefan_oltmann.oni.parser

import de.stefan_oltmann.oni.parser.common.DataReader
import de.stefan_oltmann.oni.parser.model.GameInfo
import de.stefan_oltmann.oni.parser.model.GameObject
import de.stefan_oltmann.oni.parser.model.GameObjectBehaviour
import de.stefan_oltmann.oni.parser.model.GameObjectGroup
import de.stefan_oltmann.oni.parser.model.Header
import de.stefan_oltmann.oni.parser.model.SaveGame
import de.stefan_oltmann.oni.parser.model.Template
import de.stefan_oltmann.oni.parser.model.TemplateMember
import de.stefan_oltmann.oni.parser.model.TypeCode
import de.stefan_oltmann.oni.parser.model.TypeInfo
import de.stefan_oltmann.oni.parser.model.Version
import kotlinx.serialization.json.Json
import kotlin.experimental.and

class SaveGameReader(
    bytes: ByteArray
) : DataReader(bytes) {

    fun parseHeader(): Header {

        val buildVersion = readInt32()

        val gameInfoStringByteLength = readInt32()

        val headerVersion = readInt32()
        readInt32() // Ignore

        val gameInfoStringBytes = readBytes(gameInfoStringByteLength)

        val gameInfoString = gameInfoStringBytes.decodeToString()

        val gameInfo = Json.decodeFromString<GameInfo>(gameInfoString)

        return Header(
            buildVersion = buildVersion,
            headerVersion = headerVersion,
            bodyIsCompressed = headerVersion >= 1,
            gameInfo = gameInfo
        )
    }

    fun parseTemplates(): Map<String, Template> {

        val templates = mutableMapOf<String, Template>()

        val templateCount = readInt32()

        repeat(templateCount) {

            val name = readString()
            val fieldCount = readInt32()
            val propertyCount = readInt32()

            templates[name] = Template(
                name = name,
                fields = parseTemplateMembersList(fieldCount),
                properties = parseTemplateMembersList(propertyCount)
            )
        }

        return templates
    }

    private fun parseTemplateMembersList(
        count: Int
    ): List<TemplateMember> {

        val members = mutableListOf<TemplateMember>()

        repeat(count) {

            members.add(
                TemplateMember(
                    name = readString(),
                    type = parseTypeInfo()
                )
            )
        }

        return members
    }

    private fun parseTypeInfo(): TypeInfo {

        val typeCodeAsInt = readByteAsInt()

        val inferredTypeCode = typeCodeAsInt and 0x7F

        val typeCode: TypeCode = TypeCode.of(inferredTypeCode)

        var templateName = ""

        val isValueTypeBitmask: Byte = 64.toByte()
        val isGenericTypeBitmask: Byte = 128.toByte()

        val isValueType: Boolean =
            inferredTypeCode.toByte() and isValueTypeBitmask > 0

        val isGenericType: Boolean =
            typeCodeAsInt.toByte() and isGenericTypeBitmask == isGenericTypeBitmask

        if (
            typeCode == TypeCode.UserDefined ||
            typeCode == TypeCode.Enumeration ||
            isValueType
        ) {

            val userTypeName = readString()

            check(userTypeName.isNotBlank()) {
                "Empty user type name."
            }

            templateName = userTypeName
        }

        val subTypes = mutableListOf<TypeInfo>()

        if (isGenericType) {

            check(typeCode.canBeGeneric()) {
                "Type code $inferredTypeCode marked wrongly as generic."
            }

            val subTypeCount = readByteAsInt()

            repeat(subTypeCount) {

                subTypes.add(parseTypeInfo())
            }

        } else if (typeCode == TypeCode.Array) {

            subTypes.add(parseTypeInfo())
        }

        return TypeInfo(
            typeCode = typeCode,
            isValueType = isValueType,
            isGenericType = isGenericType,
            templateName = templateName,
            subTypes = subTypes
        )
    }

    fun parseWorld(
        templates: Map<String, Template>
    ): Map<String, Any?> {

        val worldMaker = readString()

        check(worldMaker == "world") {
            "Expected maker 'world', but got '$worldMaker'"
        }

        val typeName = readString()

        check(typeName == "Klei.SaveFileRoot") {
            "Expected type Klei.SaveFileRoot, but got '$typeName'"
        }

        return parse(templates, typeName)
    }

    fun parseSettings(
        templates: Map<String, Template>
    ): Map<String, Any?> {

        val typeName = readString()

        check(typeName == "Game+Settings") {
            "Expected type Game+Settings, but got '$typeName'"
        }

        return parse(templates, typeName)
    }

    fun parseGameData(
        templates: Map<String, Template>
    ): Map<String, Any?> {

        val typeName = readString()

        check(typeName == "Game+GameSaveData") {
            "Expected type Game+GameSaveData, but got '$typeName'"
        }

        return parse(templates, typeName)
    }

    fun parseGameObjectGroups(templates: Map<String, Template>): List<GameObjectGroup> {

        val count = readInt32()

        val gameObjectGroups = mutableListOf<GameObjectGroup>()

        repeat(count) {

            val prefabName = readString()
            val instanceCount = readInt32()
            val dataLength = readInt32()

            val preParsePosition = currentPosition

            val gameObjects = mutableListOf<GameObject>()

            repeat(instanceCount) {

                gameObjects.add(
                    parseGameObject(templates)
                )
            }

            val postParsePosition = currentPosition

            val bytesRemaining = dataLength - (postParsePosition - preParsePosition);

            check(bytesRemaining == 0) {
                "Bytes remaining after parsing $prefabName should be zero, but was $bytesRemaining."
            }

            gameObjectGroups.add(
                GameObjectGroup(
                    prefabName = prefabName,
                    gameObjects = gameObjects
                )
            )
        }

        return gameObjectGroups
    }

    private fun parseGameObject(
        templates: Map<String, Template>
    ): GameObject = GameObject(
        position = readVector3f(),
        rotation = readQuaternion(),
        scale = readVector3f(),
        folder = readByteAsInt(),
        behaviours = parseGameObjectBehaviors(templates)
    )

    private fun parseGameObjectBehaviors(
        templates: Map<String, Template>
    ): List<GameObjectBehaviour> {

        val behaviours = mutableListOf<GameObjectBehaviour>()

        val behaviorCount = readInt32()

        repeat(behaviorCount) {

            behaviours.add(
                parseGameObjectBehavior(templates)
            )
        }

        return behaviours
    }

    private fun parseGameObjectBehavior(
        templates: Map<String, Template>
    ): GameObjectBehaviour {

        val name = readString()

        val dataLength = readInt32()

        val preParsePosition = currentPosition

        val templateData = parse(templates, name)

        val postParsePosition = currentPosition

        val bytesRemaining = dataLength - (postParsePosition - preParsePosition);

        check(bytesRemaining >= 0) {
            "Illegal data remaining after parsing Behavior: $bytesRemaining"
        }

        /* Skip extra bytes. */
        readBytes(bytesRemaining)

        return GameObjectBehaviour(
            name = name,
            templateData = templateData
        )
    }

    fun parseKSav(): Version {

        val marker = readBytes(KSAV_MARKER.length).decodeToString()

        check(marker == KSAV_MARKER) {
            "Expected '$KSAV_MARKER', but got '$marker'"
        }

        return Version(
            majorVersion = readInt32(),
            minorVersion = readInt32()
        )
    }

    private fun parse(
        templates: Map<String, Template>,
        templateName: String
    ): Map<String, Any?> {

        check(templateName.isNotBlank()) {
            "Provided no template name!"
        }

        val template = templates[templateName]

        checkNotNull(template) {
            "Template for '$templateName' missing in collection. File may be corrupted."
        }

        val result = mutableMapOf<String, Any?>()

        for (field in template.fields)
            result[field.name] = parse(templates, field.type)

        for (property in template.properties)
            result[property.name] = parse(templates, property.type)

        return result
    }

    private fun parse(
        templates: Map<String, Template>,
        typeInfo: TypeInfo
    ): Any? = when (typeInfo.typeCode) {

        TypeCode.Byte ->
            readByteAsInt()

        TypeCode.Boolean ->
            readByteAsInt() == 1

        TypeCode.Int32, TypeCode.Enumeration ->
            readInt32()

        TypeCode.UInt32 ->
            readUInt32AsLong()

        TypeCode.Int64 ->
            readInt64()

        TypeCode.Single ->
            readFloat()

        TypeCode.String ->
            readString()

        TypeCode.Dictionary ->
            parseDictionary(templates, typeInfo)

        TypeCode.Vector2i ->
            readVector2i()

        TypeCode.Vector2f ->
            readVector2f()

        TypeCode.Vector3f ->
            readVector3f()

        TypeCode.Array, TypeCode.List, TypeCode.HashSet ->
            parseArray(typeInfo, templates)

        TypeCode.UserDefined, TypeCode.Value ->
            parseUserDefined(typeInfo, templates)

        else -> error("Unhandled type $typeInfo")
    }

    private fun parseArray(info: TypeInfo, templates: Map<String, Template>): Any? {

        val elementType = info.subTypes[0]

        /* Skip unknown data. */
        readInt32()

        val length = readInt32();

        if (length == -1)
            return null

        check(length >= 0) {
            "Invalid array length: $length"
        }

        if (elementType.typeCode == TypeCode.Byte)
            return readBytes(length)

        if (length == 0)
            return emptyList<Any?>()

        if (elementType.isValueType) {

            val elements = mutableListOf<Any?>()

            val typeName = elementType.templateName

            repeat(length) {

                elements.add(
                    parse(
                        templates,
                        typeName
                    )
                )
            }

            return elements

        } else {

            val elements = mutableListOf<Any?>()

            repeat(length) {

                elements.add(
                    parse(templates, elementType)
                )
            }

            return elements
        }
    }

    private fun parseUserDefined(
        info: TypeInfo,
        templates: Map<String, Template>
    ): Any? {

        val templateName = info.templateName

        check(templateName.isNotBlank()) {
            "User defined value should have template name."
        }

        val dataLength = readInt32()

        if (dataLength < 0)
            return null

        val preParsePosition = currentPosition

        val data = parse(templates, templateName)

        val postParsePosition = currentPosition

        val bytesRemaining = dataLength - (postParsePosition - preParsePosition);

        check(bytesRemaining >= 0) {
            "Illegal data remaining after parsing Behavior: $bytesRemaining"
        }

        /* Skip extra bytes as we don't interpret them right now. */
        readBytes(bytesRemaining)

        return data
    }

    private fun parseDictionary(
        templates: Map<String, Template>,
        info: TypeInfo
    ): Map<Any?, Any?>? {

        val keyType = info.subTypes[0]
        val valueType = info.subTypes[1]

        /* Skip unknown data. */
        readInt32()

        val elementCount = readInt32()

        if (elementCount < 0)
            return null

        val values = mutableListOf<Any?>()

        repeat(elementCount) {

            values.add(parse(templates, valueType))
        }

        val keys = mutableListOf<Any?>()

        repeat(elementCount) {

            keys.add(parse(templates, keyType))
        }

        val results = mutableMapOf<Any?, Any?>()

        repeat(elementCount) { index ->

            results.put(keys[index], values[index])
        }

        return results
    }

    companion object {

        const val KSAV_MARKER = "KSAV"

        fun readSaveGame(bytes: ByteArray): SaveGame {

            var reader = SaveGameReader(bytes)

            val header = reader.parseHeader()
            val templates = reader.parseTemplates()

            val bodyBytesRaw = reader.readRemainingBytes()

            val bodyIsCompressed = header.bodyIsCompressed

            val bodyBytes = if (bodyIsCompressed)
                decompress(bodyBytesRaw)
            else
                bodyBytesRaw

            reader = SaveGameReader(bodyBytes)

            val world = reader.parseWorld(templates)

            val settings = reader.parseSettings(templates)

            /* Skip SimData */
            val simDataLength = reader.readInt32()
            reader.readBytes(simDataLength)

            val version = reader.parseKSav()

            val gameObjects = reader.parseGameObjectGroups(templates)

            val gameData = reader.parseGameData(templates)

            return SaveGame(
                header = header,
                templates = templates,
                world = world,
                settings = settings,
                version = version,
                gameObjects = gameObjects,
                gameData = gameData
            )
        }
    }
}
