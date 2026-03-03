plugins { java }

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.7.0")
    implementation("io.projectreactor:reactor-core:3.6.3")

    testImplementation("org.testcontainers:kafka:1.19.7")
    testImplementation("org.testcontainers:junit-jupiter:1.19.7")
}
