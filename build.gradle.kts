plugins {
    alias(libs.plugins.nexus.publish)
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    version = "1.0.10-SNAPSHOT"
    group = "dev.httpmarco.evelon"

    repositories {
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        "annotationProcessor"(rootProject.libs.lombok)
        "implementation"(rootProject.libs.bundles.utils)
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }

    extensions.configure<PublishingExtension> {
        publications {
            create("library", MavenPublication::class.java) {
                from(project.components.getByName("java"))

                pom {
                    name.set(project.name)
                    url.set("https://github.com/httpmarco/evelon")
                    description.set("Reflection/Data libary")
                    licenses {
                        license {
                            name.set("Apache License")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0")
                        }
                    }
                    developers {
                        developer {
                            name.set("Mirco Lindenau")
                            email.set("mirco.lindenau@gmx.de")
                        }
                    }

                    scm {
                        url.set("https://github.com/httpmarco/evelon")
                        connection.set("https://github.com/httpmarco/evelon.git")
                    }
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/releases/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(System.getenv("ossrhUsername")?.toString() ?: "")
            password.set(System.getenv("ossrhPassword")?.toString() ?: "")
        }
    }
    useStaging.set(!project.rootProject.version.toString().endsWith("-SNAPSHOT"))
}