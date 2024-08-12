dependencies {
    testImplementation(libs.gson)
    implementation(project(":evelon-common"))
    testRuntimeOnly(project(":evelon-common"))
    testImplementation(project(":evelon-common"))

    testImplementation(project(":evelon-sql-parent"))
    testImplementation(project(":evelon-sql-h2"))
    testImplementation(project(":evelon-sql-mariadb"))
    testImplementation(project(":evelon-sql-mysql"))
    testImplementation(project(":evelon-sql-postgresql"))

    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}