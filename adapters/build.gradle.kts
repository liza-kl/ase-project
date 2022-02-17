val ktorVersion: String by project
val exposedVersion: String by project
dependencies {
    implementation(projects.useCases)
    implementation(projects.domain)
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
}
