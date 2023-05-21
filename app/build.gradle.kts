plugins {
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.3.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

group = "com.koltsov.captain.calculator"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

extra["kotlin_version"] = "1.8.21"
extra["kotlin_code_style"] = "official"
extra["ktor_version"] = "2.3.0"
extra["logback_version"] = "1.4.7"
extra["exposed_version"] = "0.41.1"
extra["postgres_version"] = "42.5.1"
extra["flyway_version"] = "9.18.0"
extra["koin_version"] = "3.4.0"
extra["aws_version"] = "1.12.472"

dependencies {
    implementation(project(":sdk"))

    implementation("io.ktor:ktor-server-core-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-host-common-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-netty-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-config-yaml:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-auto-head-response-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-status-pages-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-default-headers-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-call-logging-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-call-id-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:${property("ktor_version")}")
    implementation("io.ktor:ktor-serialization-jackson-jvm:${property("ktor_version")}")

    implementation("org.jetbrains.exposed:exposed-core:${property("exposed_version")}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${property("exposed_version")}")
    implementation("org.jetbrains.exposed:exposed-dao:${property("exposed_version")}")
    implementation("org.postgresql:postgresql:${property("postgres_version")}")
    implementation("org.flywaydb:flyway-core:${property("flyway_version")}")

    implementation("ch.qos.logback:logback-classic:${property("logback_version")}")

    implementation("io.insert-koin:koin-ktor:${property("koin_version")}")
    implementation("io.insert-koin:koin-logger-slf4j:${property("koin_version")}")

    implementation(platform("com.amazonaws:aws-java-sdk-bom:${property("aws_version")}"))
    implementation("com.amazonaws:aws-java-sdk-s3")

    testImplementation("io.ktor:ktor-server-tests-jvm:${property("ktor_version")}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${property("kotlin_version")}")
}