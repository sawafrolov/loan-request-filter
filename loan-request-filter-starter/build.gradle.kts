import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.github.sawafrolov"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.named("bootJar") {
    enabled = false
}

repositories {
    mavenCentral()
}

dependencies {

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Commons
    implementation(project(":loan-request-filter-commons"))

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // Camunda DMN Engine
    implementation("org.camunda.bpm.dmn:camunda-engine-dmn:${property("camundaDmnEngineVersion")}")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}
