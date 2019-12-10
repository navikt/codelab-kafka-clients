plugins {
    id("java")
}

repositories {
    mavenCentral()
}

val log4j2Version = "2.12.1"
val kafkaClientsVersion = "2.3.1"

val libs = "$projectDir/libs"
val implementationLib = "$libs/implementation"


dependencies {

    if(gradle.startParameter.isOffline) {
         implementation(fileTree(implementationLib))
    } else {
        implementation(group = "org.apache.kafka", name = "kafka-clients", version = kafkaClientsVersion)
        implementation(group = "org.apache.logging.log4j", name = "log4j-api", version = log4j2Version)
        implementation(group = "org.apache.logging.log4j", name = "log4j-core", version = log4j2Version)
        implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = log4j2Version)
        implementation(group = "com.google.code.gson", name = "gson", version = "2.8.6")
    }

}

val copyDeps by tasks.creating(Copy::class) {
   from(
       configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { it.absolutePath }.toTypedArray()
   ).into(implementationLib)
}
