plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "com.marrrang"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-web:6.1.3")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.1")
    implementation(kotlin("reflect"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.2.1")

    testImplementation(kotlin("test"))
}