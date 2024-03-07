dependencies {
    implementation(project(":evelon-common"))
    runtimeOnly(project(":evelon-common"))

    //sql
    implementation(project(":evelon-sql-h2"))
    implementation(project(":evelon-sql-parent"))

    runtimeOnly(libs.gson)

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