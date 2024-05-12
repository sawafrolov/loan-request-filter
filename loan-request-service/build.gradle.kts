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
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Commons
    implementation(project(":loan-request-filter-commons"))

    // Starter
    implementation(project(":loan-request-filter-starter"))

    // Liquibase
    implementation("org.liquibase:liquibase-core")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka")

    // MapStruct
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

    // Project Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // MapStruct Annotation Processor
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

    // QueryDSL Annotation Processor
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:general")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.ninja-squad:springmockk:${property("springMockkVersion")}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
