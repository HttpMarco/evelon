plugins {
    java
    `java-library`
    `maven-publish`
    //id ("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.bytemc"
version = "1.5.2-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        url = uri("https://artifactory.bytemc.de/artifactory/bytemc-public/")
    }
}

val lombok = "1.18.30"

dependencies {
    compileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")

    compileOnly("org.jetbrains:annotations:24.0.1")

    api("org.mariadb.jdbc:mariadb-java-client:3.2.0") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    api("com.mysql:mysql-connector-j:8.2.0")
    api("net.bytemc:hikariCP:5.0.2")
    api("com.google.code.gson:gson:2.10.1")
    api("org.mongodb:mongodb-driver-sync:4.11.0")
    api("com.h2database:h2:2.2.224")
    api("org.postgresql:postgresql:42.6.0")

    testCompileOnly("org.projectlombok:lombok:$lombok")
    testAnnotationProcessor("org.projectlombok:lombok:$lombok")

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
