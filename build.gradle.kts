plugins {
    id("java")
}

repositories {
    mavenCentral()
}

val log4j2Version = "2.11.1"
val kafkaClientsVersion = "2.0.1"


dependencies {
    implementation(group = "org.apache.kafka", name = "kafka-clients", version = kafkaClientsVersion)
    implementation(group = "org.apache.logging.log4j", name = "log4j-api", version = log4j2Version)
    implementation(group = "org.apache.logging.log4j", name = "log4j-core", version = log4j2Version)
    implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = log4j2Version)
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
}