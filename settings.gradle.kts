plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "java-expert"

include(
    "modules:language-basics",
    "modules:immutability-concurrency",
    "modules:streams-lambdas",
    "modules:io-serde",
    "modules:testing-quality",
    "modules:spring-boot-hexagonal",
    "modules:persistence-jooq",
    "modules:kafka-reactive",
    "modules:performance-profiling",
    "modules:interop"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include("modules:interview-prep")
