plugins { java }

dependencies {
    implementation("org.jooq:jooq:3.19.2")
    implementation("org.flywaydb:flyway-core:10.10.0")
    runtimeOnly("org.postgresql:postgresql:42.7.4")

    testImplementation("org.testcontainers:junit-jupiter:1.19.7")
    testImplementation("org.testcontainers:postgresql:1.19.7")
}
