val kotestVersion: String by project
val kotlinVersion: String by project

group = "de.dhbw.ka"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

subprojects {
    apply(plugin = "kotlin")
    repositories {
        mavenCentral()
    }

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}}
