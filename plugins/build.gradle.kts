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
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.postgresql:postgresql:42.2.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

}

