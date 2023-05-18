plugins {
    kotlin("jvm") version "1.7.22"
    id("java-library")
}

group = "com.koltsov.captain.calculator"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
}

java {
    withSourcesJar()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}