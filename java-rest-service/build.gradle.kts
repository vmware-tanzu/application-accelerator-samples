
plugins {
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("java")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

val springdocVersion = "2.8.4"

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:${springdocVersion}")

    // Database
    // #IF(#databaseType == 'postgres')
    runtimeOnly("org.postgresql:postgresql")
    // #ELSE
    runtimeOnly("com.mysql:mysql-connector-j")
    // #ENDIF

    // Infrastructure
    // #IF(#databaseMigrationTool == 'flyway')
    implementation("org.flywaydb:flyway-core")
    //   #IF(#databaseType == 'postgres')
    implementation("org.flywaydb:flyway-database-postgresql")
    //   #ENDIF
    // #ELSE
    implementation("org.liquibase:liquibase-core")
    // #ENDIF

    // Observability support
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Test
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    // #IF(#databaseIntegrationTestType == 'in-memory')
    testImplementation("com.h2database:h2")
    // #ELSE
    //   #IF(#databaseType == 'postgres')
    testImplementation("org.testcontainers:postgresql")
    //   #ELSE
    testImplementation("org.testcontainers:mysql")
    //   #ENDIF
    // #ENDIF
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    enabled = false
}
