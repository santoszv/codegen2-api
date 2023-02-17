group = "mx.com.inftel.codegen"
version = "2.0.0-SNAPSHOT"

plugins {
    java
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {

    // Jakarta EE
    compileOnly("jakarta.platform:jakarta.jakartaee-api:10.0.0")

}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>().configureEach {
    options.encoding = "UTF-8"
}

publishing {
    repositories {
        maven {
            setUrl(file("$projectDir/build/repo"))
        }
    }

    publications {
        create<MavenPublication>("codegen2Api") {
            from(components["java"])
            pom {
                name.set("${project.group}:${project.name}")
                description.set("Codegen2 API")
                url.set("https://github.com/santoszv/codegen2-api")
                inceptionYear.set("2022")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("santoszv")
                        name.set("Santos Zatarain Vera")
                        email.set("santoszv@inftel.com.mx")
                        url.set("https://www.inftel.com.mx")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/santoszv/codegen2-api")
                    developerConnection.set("scm:git:https://github.com/santoszv/codegen2-api")
                    url.set("https://github.com/santoszv/codegen2-api")
                }
            }
            signing.sign(this)
        }
    }
}

signing {
    useGpgCmd()
}