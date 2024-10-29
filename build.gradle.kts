plugins {
    application
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    id("io.ktor.plugin") version "2.3.7"
    id("me.qoomon.git-versioning") version "6.4.4"
}

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

group = "org.mapsnotincluded"

gitVersioning.apply {

    refs {
        /* Main branch contains the current dev version */
        branch("main") {
            version = "\${commit.short}"
        }
    }

    /* Fallback if branch was not found (for feature branches) */
    rev {
        version = "\${commit.short}"
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
}

dependencies {

    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-cbor:$ktorVersion")

    implementation("io.ktor:ktor-server-cors:$ktorVersion")

    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
    implementation("org.mongodb:bson-kotlinx:5.2.0")

    implementation("com.menecats:polybool-java:1.0.1")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
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
