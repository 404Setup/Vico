import java.util.Locale

rootProject.name = "VicoLib"
buildscript {
    repositories {
        maven("https://maven-central-asia.storage-download.googleapis.com/maven2/")
    }
}

var subProjects = listOf("API", "Spigot", "Paper", "Folia", "Bungee", "Velocity", "Common")

for (name in subProjects) {
    val projName = name.lowercase(Locale.getDefault())
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}

/*
List<String> subProjects = List.of("API", "Spigot", "Paper", "Folia", "Bungee", "Velocity", "Common")
for (name in subProjects) {
    String projName = name.toLowerCase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")?.projectDir = file(name)
}
 */