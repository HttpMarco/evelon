allprojects {
    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "maven-publish")

    version = "2.0.0-SNAPSHOT"
    group = "dev.httpmarco.evelon"

    repositories {
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        "compileOnly"(rootProject.libs.bundles.utils)
        "annotationProcessor"(rootProject.libs.lombok)
        "compileOnly"(rootProject.libs.gson)
        "implementation"(rootProject.libs.bundles.osgan)
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }
}