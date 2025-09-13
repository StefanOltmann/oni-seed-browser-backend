plugins {
    application
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.serialization") version "2.2.20"
    id("io.ktor.plugin") version "3.1.1"
    id("me.qoomon.git-versioning") version "6.4.4"
    id("io.sentry.jvm.gradle") version "5.3.0"
    id("app.cash.sqldelight") version "2.1.0"
}

val ktorVersion: String by project
val kotlinVersion: String by project

group = "org.mapsnotincluded"

gitVersioning.apply {

    refs {
        /* The main branch contains the current dev version */
        branch("main") {
            version = "\${commit.short}"
        }
    }

    /* Fallback if the branch was not found (for feature branches) */
    rev {
        version = "\${commit.short}"
    }
}

kotlin {
    jvmToolchain(jdkVersion = 17)
}

sqldelight {
    databases {
        create("SearchIndexDatabase") {
            packageName.set("org.mapsnotincluded.search")
        }
    }
}

application {

    mainClass.set("ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

sourceSets {
    main {
        kotlin {
            srcDir(layout.buildDirectory.dir("generated/source"))
        }
    }
}

repositories {
    mavenCentral()
    maven(url = "https://central.sonatype.com/repository/maven-snapshots/")
}

dependencies {

    implementation("de.stefan-oltmann:oni-seed-browser-model:8124919-SNAPSHOT")

    /*
     * Ktor server
     */

    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")

    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")

    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-server-html-builder:$ktorVersion")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-protobuf:${ktorVersion}")

    /*
     * Ktor client
     */

    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

    /*
     * MongoDB handling
     */
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
    implementation("org.mongodb:bson-kotlinx:5.2.0")

    /*
     * SQLite
     */
    implementation("app.cash.sqldelight:sqlite-driver:2.1.0")

    /*
     * Biome path optimization
     */
    implementation("de.stefan-oltmann:polybool-kotlin:0.1.0")

    /*
     * JWT handling
     */
    implementation("com.auth0:java-jwt:4.5.0")

    implementation("io.minio:minio:8.5.17")

    /*
     * Unit tests
     */
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

// region Version
project.afterEvaluate {

    logger.lifecycle("Generate Version.kt")

    val outputDir = layout.buildDirectory.file("generated/source/").get().asFile

    outputDir.mkdirs()

    val file = File(outputDir.absolutePath, "Version.kt")

    file.printWriter().use { writer ->

        writer.println("const val VERSION: String = \"$version\"")

        writer.flush()
    }
}
// endregion
