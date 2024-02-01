plugins {
    alias(libs.plugins.nexusPublish)
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "maven-publish")

    version = "2.0.1-SNAPSHOT"
    group = "dev.httpmarco.evelon"

    repositories {
        mavenCentral()
        maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        "compileOnly"(rootProject.libs.lombok)
        "compileOnly"(rootProject.libs.annotations)
        "annotationProcessor"(rootProject.libs.lombok)
        "compileOnly"(rootProject.libs.gson)
        "compileOnly"(rootProject.libs.bundles.osgan)
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }

    extensions.configure<PublishingExtension> {
        publications.apply {
            create("maven", MavenPublication::class.java).apply {
                from(components.getByName("java"))

                pom.apply {
                    name.set(project.name)
                    url.set("https://github.com/HttpMarco/evelon")
                    description.set("Database layer object mapper")

                    developers {
                        developer {
                            id.set("HttpMarco")
                            email.set("mirco.lindenau@gmx.de")
                        }
                    }

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    scm {
                        tag.set("HEAD")
                        url.set("git@github.com:httpmarco/evelon.git")
                        connection.set("scm:git:git@github.com:httpmarco/evelon.git")
                        developerConnection.set("scm:git:git@github.com:httpmarco/evelon.git")
                    }
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(System.getenv("ossrhUsername") ?: "")
            password.set(System.getenv("ossrhPassword") ?: "")
        }
    }
    useStaging.set(!rootProject.version.toString().endsWith("-SNAPSHOT"))
}