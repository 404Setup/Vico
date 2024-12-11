import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("eclipse")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.8"
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "one.tranic"
version = "24.12.0-Beta1"

val targetJavaVersion = 17

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType<JavaCompile>().configureEach {
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

repositories {
    maven("https://maven-central-asia.storage-download.googleapis.com/maven2/")
    maven("https://s01.oss.sonatype.org/content/groups/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":api", configuration = "shadow"))
    implementation(project(":common", configuration = "shadow"))
    implementation(project(":velocity", configuration = "shadow"))
    implementation(project(":folia", configuration = "shadow"))
    implementation(project(":bungee", configuration = "shadow"))

    compileOnly("org.yaml:snakeyaml:2.3")
    implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4") {
        exclude("org.yaml", "snakeyaml")
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "com.gradleup.shadow")

    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    java.sourceCompatibility = javaVersion

    project.version = rootProject.version as String

    repositories {
        maven("https://maven-central-asia.storage-download.googleapis.com/maven2/")
        maven("https://s01.oss.sonatype.org/content/groups/public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.codemc.io/repository/maven-public/")
        maven("https://jitpack.io")
    }

    dependencies {
        compileOnly("com.google.code.gson:gson:2.11.0")
        implementation("org.jetbrains:annotations:24.1.0")
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName = "${project.name}-${project.version}.jar"

    exclude("dev/velocitypowered")

    /*minimize {
        exclude("**.html")
        exclude("com/google")
    }*/
}