dependencies {
    implementation(libs.h2)

    compileOnly(project(":evelon-common"))
    compileOnly(project(":evelon-sql-parent"))

    //testing
    testImplementation(project(":evelon-sql-parent"))
    testImplementation(project(":evelon-common"))
    testImplementation(libs.hikari)
    testImplementation(libs.bundles.logger)
}