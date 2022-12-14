
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("java")
}

group = "com.vmware.tap.accelerators"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val springdocVersion = "1.6.9"
val springBootVersion: String by project
extra["snakeyaml.version"] = "1.32"

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

    // Test
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    enabled = false
}

springBoot {
    buildInfo {
        properties {
            additional = mapOf(
                "spring.boot.version" to springBootVersion
            )
        }
    }
}