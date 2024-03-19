dependencies {
    implementation(project(":evelon-common"))

    testImplementation(project(":evelon-sql-h2"))
    testImplementation(project(":evelon-sql-parent"))
    testImplementation(project(":evelon-common"))

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}