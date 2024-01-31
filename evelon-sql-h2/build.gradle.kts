dependencies {
    implementation(libs.h2)

    compileOnly(project(":evelon-common"))
    compileOnly(project(":evelon-sql-parent"))
}