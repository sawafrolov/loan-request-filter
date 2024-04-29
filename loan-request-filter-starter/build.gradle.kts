import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
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
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}
