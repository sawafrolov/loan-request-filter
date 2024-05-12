rootProject.name = "loan-request-filter"

include("loan-request-filter-commons")
include("loan-request-filter-starter")
include("loan-request-service")
include("filter-service")

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.lombok") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion

        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
    }
}
