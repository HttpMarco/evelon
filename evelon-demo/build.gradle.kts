dependencies {
    implementation(project(":evelon-common"))
    runtimeOnly(project(":evelon-common"))

    //sql
    implementation(project(":evelon-sql-h2"))
    implementation(project(":evelon-sql-parent"))

    runtimeOnly(libs.gson)
}