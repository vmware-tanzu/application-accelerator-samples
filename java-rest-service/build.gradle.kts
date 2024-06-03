
plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("java")
}

group = "com.vmware.tap.accelerators"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val springdocVersion = "2.5.0"

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

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:${springdocVersion}")

    // Database
    // #IF(#databaseType == 'postgres')
    runtimeOnly("org.postgresql:postgresql")
    // #ENDIF
    // #IF(#databaseType == 'mysql')
    runtimeOnly("com.mysql:mysql-connector-j")
    // #ENDIF
    // #IF(#databaseIntegrationTestType == 'in-memory')
    testImplementation("com.h2database:h2")
    // #ENDIF

    // Infrastructure
    // #IF(#databaseMigrationTool == 'flyway')
    implementation("org.flywaydb:flyway-core")
    // #ENDIF
    // #IF(#databaseMigrationTool == 'flyway' && #databaseType == 'mysql')
    implementation("org.flywaydb:flyway-mysql")
    // #ENDIF
    // #IF(#databaseMigrationTool == 'flyway' && #databaseType == 'postgres')
    implementation("org.flywaydb:flyway-database-postgresql")
    // #ENDIF
    // #IF(#databaseMigrationTool == 'liquibase')
    implementation("org.liquibase:liquibase-core")
    // #ENDIF
    // #IF(#databaseIntegrationTestType == 'testcontainers' && #databaseType == 'postgres')
    testImplementation("org.testcontainers:postgresql")
    // #ENDIF
    // #IF(#databaseIntegrationTestType == 'testcontainers' && #databaseType == 'mysql')
    testImplementation("org.testcontainers:mysql")
    // #ENDIF

    // Observability support
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Test
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    enabled = false
}
