package com.koltsov.captain.calculator.items.service.infrastructure.ktor.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    HikariDataSource(
        HikariConfig().apply {
            driverClassName = environment.config.property("db.driver").getString()
            jdbcUrl = environment.config.property("db.url").getString()
            username = environment.config.property("db.username").getString()
            password = environment.config.property("db.password").getString()
            maximumPoolSize = 10
            validate()
        }
    )
        .also {
            Database.connect(it)
            Flyway
                .configure()
                .loggers("slf4j")
                .dataSource(it)
                .load()
                .migrate()
        }
}
