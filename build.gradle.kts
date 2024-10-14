import java.text.SimpleDateFormat
import java.util.Date

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.virep"

val isDev = project.hasProperty("dev") && project.property("dev") == "true"
var versionNumber = "1.0"

version = if (isDev) {
    val date = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())
    "$versionNumber-SNAPSHOT_$date"
} else {
    "$versionNumber"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.1.2")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("org.reflections:reflections:0.10.2")
}

application {
    mainClass.set("org.virep.ShadowRealm.Main")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
    }
}

tasks.named<JavaExec>("run") {
    doFirst {
        // Gradle doesn't like -Pdev=true -Ddev=true
        // need to pass it through task directly
        if (project.hasProperty("dev")) {
            jvmArgs("-Ddev=${project.property("dev")}")
        }
    }
}



tasks.test {
    useJUnitPlatform()
}