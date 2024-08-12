dependencies {
    api(libs.hikari)

    implementation(libs.mysql)
    api(project(":evelon-common"))
    api(project(":evelon-sql-parent"))
}