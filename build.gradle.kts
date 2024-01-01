plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
}

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

group = "de.stefan_oltmann.oni"
version = "0.0.1"

application {

    mainClass.set("de.stefan_oltmann.oni.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

