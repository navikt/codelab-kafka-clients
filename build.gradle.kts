plugins {
    id("java")
}

repositories {
    mavenCentral()
}

val log4j2_version = "2.11.1"
val kafka_clients_version = "2.0.1"


dependencies {
    implementation(group = "org.apache.kafka", name = "kafka-clients", version = kafka_clients_version)
    implementation(group = "org.apache.logging.log4j", name = "log4j-api", version = log4j2_version)
    implementation(group = "org.apache.logging.log4j", name = "log4j-core", version = log4j2_version)
    implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = log4j2_version)
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
}