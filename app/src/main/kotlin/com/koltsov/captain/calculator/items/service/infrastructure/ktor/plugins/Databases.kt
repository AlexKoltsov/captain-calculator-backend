package com.koltsov.captain.calculator.items.service.infrastructure.ktor.plugins

import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity.ImagesTable
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity.ItemsTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val database = Database.connect(
        url = environment.config.property("postgres.url").getString(),
        user = environment.config.property("postgres.user").getString(),
        password = environment.config.property("postgres.password").getString(),
    )
    transaction {
        SchemaUtils.create(ItemsTable, ImagesTable)
    }
}
