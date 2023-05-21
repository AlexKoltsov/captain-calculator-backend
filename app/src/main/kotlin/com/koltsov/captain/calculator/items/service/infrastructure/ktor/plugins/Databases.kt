package com.koltsov.captain.calculator.items.service.infrastructure.ktor.plugins

import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val url = environment.config.property("postgres.url").getString()
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()
    val database = Database.connect(url = url, user = user, password = password)
    Flyway
        .configure()
        .dataSource(url, user, password)
        .load()
        .migrate()
}
