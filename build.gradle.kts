plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.36.0"
}

allprojects {
    group = "com.alessiodp.libby"
    version = "2.0.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.vanniktech.maven.publish")

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.1")
        testCompileOnly("org.jetbrains:annotations:24.0.1")
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.test {
        useJUnitPlatform()
    }

    mavenPublishing {
        publishToMavenCentral(true)
        coordinates("net.flectone", project.name, "2.0.0")

        pom {
            name = "Libby"
            description = "A runtime dependency management library for plugins running in Java-based Minecraft server platforms."
            url = "https://github.com/AlessioDP/libby"

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/license/mit/")
                }
            }

            developers {
                developer {
                    id = "AlessioDP"
                    email = "me@alessiodp.com"
                }
            }

            scm {
                connection = "scm:git:git://github.com/AlessioDP/libby.git"
                developerConnection = "scm:git:git@github.com:AlessioDP/libby.git"
                url = "https://github.com/AlessioDP/libby"
            }
        }

        signAllPublications()
    }
}