plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
    alias(libs.plugins.git.versioning)
    alias(libs.plugins.sentry)
    alias(libs.plugins.sqldelight)
}

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

    implementation(libs.oni.seed.browser.model)

    /*
     * Ktor server
     */
    implementation(libs.bundles.ktor.server)

    /*
     * Ktor client
     */
    implementation(libs.ktor.client.okhttp)

    /*
     * MongoDB handling
     */
    implementation(libs.bundles.mongodb)

    /*
     * SQLite
     */
    implementation(libs.sqldelight.sqlite.driver)

    /*
     * Biome path optimization
     */
    implementation(libs.polybool.kotlin)

    /*
     * JWT handling
     */
    implementation(libs.java.jwt)

    implementation(libs.minio)

    implementation("com.github.luben:zstd-jni:1.5.6-1")

    /*
     * Unit tests
     */
    testImplementation(libs.kotlin.test.junit)
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
