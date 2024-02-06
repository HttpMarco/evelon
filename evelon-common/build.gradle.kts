dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.gson)
}

tasks.test {
    useJUnitPlatform()
}