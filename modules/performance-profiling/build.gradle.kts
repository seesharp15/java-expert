plugins {
    java
    id("me.champeau.jmh")
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:1.37")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmh(project(":modules:immutability-concurrency"))
}
