import org.gradle.api.reporting.ReportingExtension

plugins {
    java
    id("info.solidsoft.pitest") version "1.19.0-rc.1"
}

// Gradle 9 friendly configuration; pitest >=1.19 handles reporting paths internally.
extensions.configure<ReportingExtension> { /* keep for future reporting tweaks if needed */ }

pitest {
    junit5PluginVersion.set("1.2.1")
    pitestVersion.set("1.15.0")
    targetClasses.set(listOf("dev.expert.testing.*"))
    outputFormats.set(listOf("XML", "HTML"))
    timestampedReports.set(false)
}

dependencies {
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("net.jqwik:jqwik:1.8.2")
    testImplementation("org.testcontainers:junit-jupiter:1.19.7")
    testImplementation("org.testcontainers:postgresql:1.19.7")
}
