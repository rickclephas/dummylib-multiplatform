import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

group = "com.github.asm0dey.dummylib"
version = "0.0.1"

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("Dummy Kotlin Multiplatform library")
            description.set("Dummy library to test deployment to Maven Central")
            url.set("https://github.com/asm0dey/dummylib-multiplatform")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("asm0dey")
                    name.set("Pasha Finkelshteyn")
                    email.set("asm0dey@jetbrains.com")
                }
            }
            scm {
                url.set("https://github.com/asm0dey/dummylib-multiplatform")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}