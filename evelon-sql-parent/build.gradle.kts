dependencies {
    implementation(libs.hikari)
    compileOnly(project(":evelon-common"))

    //testing
    testImplementation(project(":evelon-common"))
    testImplementation(libs.hikari)
}