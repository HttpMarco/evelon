plugins {
    java
    `java-library`
    `maven-publish`
    //id ("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.bytemc"
version = "1.5.0"

repositories {
    mavenCentral()

    maven {
        url = uri("https://artifactory.bytemc.de/artifactory/bytemc-public/")
    }
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.apache.logging.log4j:log4j-core:2.22.0")

}

tasks.withType<Test> {
    useJUnitPlatform();
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            this.groupId = project.group.toString()
            this.artifactId = project.name
            this.version = project.version.toString()
        }
    }

    repositories {
        maven {
            name = "bytemc"
            url = uri("https://artifactory.bytemc.de/artifactory/bytemc-public/")
            credentials {
                username = System.getenv("BYTEMC_REPO_USERNAME")
                password = System.getenv("BYTEMC_REPO_PASSWORD")
            }
        }
    }
}
