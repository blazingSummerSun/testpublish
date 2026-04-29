plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "io.github.blazingsummersun"
version = "0.0.1"
description = "testpublish"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

mavenPublishing {
    // This helper sets up the POM for Maven Central/Sonatype requirements
    coordinates("io.github.blazingsummersun", "testpublish", "0.0.1")

    pom {
        name.set("Test Publish Library")
        description.set("A test project for Maven Central publication")
        url.set("https://github.com")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://apache.org")
            }
        }
        developers {
            developer {
                id.set("blazingsummersun")
                name.set("Adel")
                email.set("eternalblessed99@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:git://://github.com")
            developerConnection.set("scm:git:ssh://github.com:blazingsummersun/your-repo-name.git")
            url.set("https://github.com")
        }
    }

    // This tells the plugin to publish to the modern Central Portal
//    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)

    // Automatically sign artifacts
    signAllPublications()
}
