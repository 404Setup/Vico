repositories {}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT")

    implementation(project(":common"))
    implementation(project(":velocity"))
    implementation(project(":bungee"))
    implementation(project(":paper"))
    implementation(project(":spigot"))
}