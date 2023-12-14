plugins {
    java
    `java-library`
    `maven-publish`
    //id ("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.bytemc"
version = "2.0.0"

allprojects {
    version = rootProject.version
    group = "net.bytemc.evelon"
    repositories {
        mavenCentral()
        maven {
            url = uri("https://artifactory.bytemc.de/artifactory/bytemc-public/")
        }
    }
}

subprojects {

    apply(plugin = "java")

    dependencies {
        compileOnly(rootProject.libs.annotations)
        compileOnly(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }
}