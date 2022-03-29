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
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("com.h2database:h2:2.1.210")
    implementation("com.zaxxer:HikariCP:5.0.1")
    testImplementation("io.kotest.extensions:kotest-assertions-ktor:1.0.3")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    implementation("io.mockk:mockk:1.11.0")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")

}

