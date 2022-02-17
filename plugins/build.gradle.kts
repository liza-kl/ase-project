val ktorVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project

plugins {
    application
}

application {
    mainClass.set("de.dhbw.ka.ApplicationKt")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.useCases)
    implementation(projects.adapters)
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("ch.qos.logback:logback-classic:1.2.10")
    implementation("org.postgresql:postgresql:42.3.2")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

}

