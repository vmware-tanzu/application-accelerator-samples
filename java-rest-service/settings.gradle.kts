rootProject.name = "rest-service-db"


pluginManagement {
    val springBootVersion: String by settings

    plugins {
        id("org.springframework.boot").version(springBootVersion)
    }
}
