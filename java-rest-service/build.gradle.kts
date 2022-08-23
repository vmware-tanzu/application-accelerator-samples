
plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    id("java")
}

group = "com.vmware.tap.accelerators"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val springdocVersion = "1.6.9"
val testcontainersVersion = "1.17.3"

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springdoc:springdoc-openapi-webmvc-core:${springdocVersion}")

    // Infrastructure
    implementation("org.flywaydb:flyway-core")
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql")

    // Test
    testImplementation ("org.springframework.boot:spring-boot-starter-test")

    testImplementation(platform("org.testcontainers:testcontainers-bom:${testcontainersVersion}"))
    testImplementation ("org.testcontainers:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
