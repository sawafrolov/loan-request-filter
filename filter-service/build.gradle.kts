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

repositories {
    mavenCentral()
}

springBoot {
    buildInfo()
}

dependencies {

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

    // Jackson for Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Commons
    implementation(project(":loan-request-filter-commons"))

    // Starters
    implementation(project(":loan-request-filter-camunda-starter"))

    // Liquibase
    implementation("org.liquibase:liquibase-core")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka")

    // MapStruct
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

    // Camunda DMN Engine
    implementation("org.camunda.bpm.dmn:camunda-engine-dmn:${property("camundaDmnEngineVersion")}")

    // Project Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // MapStruct Annotation Processor
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.ninja-squad:springmockk:${property("springMockkVersion")}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
