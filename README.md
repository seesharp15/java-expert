# Java Expert Sprint

This is a multi-module Gradle project aimed at upskilling from Scala/C# to expert-level Java. Each module contains failing tests or TODOs for you to implement.

## Modules
- `language-basics`: generics, sealed types, pattern matching.
- `immutability-concurrency`: multiple rate limiter implementations, racing tasks helper.
- `streams-lambdas`: collectors, sliding windows, parallel pitfalls.
- `io-serde`: zero-copy file IO, length-prefixed protocol, Jackson polymorphism.
- `testing-quality`: Mockito + property-based testing (jqwik) around a checkout service.
- `spring-boot-hexagonal`: hexagonal REST API with in-memory repo (replace with DB as you like).
- `persistence-jooq`: optimistic locking patterns (tests disabled until container wired).
- `kafka-reactive`: Reactor ↔ Kafka bridge (tests disabled until container wired).
- `performance-profiling`: JMH benchmark harness for rate limiters.
- `interop`: JNI + gRPC stubs.

## Building / running
1. Install Gradle 8.7+ (or use SDKMAN / Homebrew).
2. From repo root run `gradle wrapper` once to generate `./gradlew` (wrapper jar is not committed here).
3. Run the suite: `./gradlew test` (or `gradle test` if wrapper unavailable).
4. Run mutation tests: `./gradlew :modules:testing-quality:pitest`.
5. Run JMH benchmarks: `./gradlew :modules:performance-profiling:jmh`.
6. Spring Boot API: `./gradlew :modules:spring-boot-hexagonal:bootRun` then call `POST /quotes` etc.

Notes:
- Disabled tests in `persistence-jooq`, `kafka-reactive`, and JNI parts of `interop` are placeholders for containerized runs.
- Source files contain `TODO` markers to fill in; tests will fail until implemented.
