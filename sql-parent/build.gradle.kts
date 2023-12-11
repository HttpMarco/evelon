plugins {
    id("java")
}

group = "net.bytemc.evelon"
version = "1.5.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":core"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}