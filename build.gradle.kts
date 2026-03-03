import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    idea
    jacoco
    id("info.solidsoft.pitest") version "1.19.0-rc.1" apply false
    id("me.champeau.jmh") version "0.7.2" apply false
    id("org.springframework.boot") version "3.3.0" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    group = "dev.expert"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
            exceptionFormat = TestExceptionFormat.FULL
            events("failed", "skipped", "passed")
        }
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.assertj:assertj-core:3.25.3")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}
