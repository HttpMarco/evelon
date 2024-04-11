dependencies {
    api(libs.hikari)
    
    implementation(libs.h2)
    implementation(project(":evelon-common"))
    implementation(project(":evelon-sql-parent"))
}