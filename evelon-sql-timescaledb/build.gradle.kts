dependencies {
    api(libs.hikari)

    implementation(libs.postgresql)
    api(project(":evelon-common"))
    api(project(":evelon-sql-parent"))
}