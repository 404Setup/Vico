repositories {}

dependencies {
    implementation(project(":common", configuration = "shadow"))
    implementation("net.kyori:adventure-platform-bungeecord:4.3.4")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

    compileOnly("net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT")
}

tasks.named<ProcessResources>("processResources") {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("bungee.yml") {
        expand(props)
    }
}