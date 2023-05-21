package com.koltsov.captain.calculator.items.service.infrastructure.web.adapter.out

import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCaseCommand
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.fromDomain
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.toUseCaseCommand
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory

fun Application.itemsRouting() = routing {
    val log = LoggerFactory.getLogger(javaClass)

    route("/api/v1/items") {

        get {
            val findItemsUseCase: FindItemsUseCase by inject(FindItemsUseCase::class.java)

            val name = call.request.queryParameters["name"]
            val description = call.request.queryParameters["description"]
            findItemsUseCase.findItems(
                FindItemsUseCaseCommand(name = name, description = description)
            )
                .map { fromDomain(it) }
                .let { call.respond(it) }
        }

        post {
            val createItemUseCase: CreateItemUseCase by inject(CreateItemUseCase::class.java)

            call.receive<ItemUpsertRequest>().toUseCaseCommand()
                .let { createItemUseCase.create(it) }
                .let { fromDomain(it) }
                .let { call.respond(it) }
        }

        put("{id}") {
            val id = call.parameters["id"] ?: throw RuntimeException("ID required")
            TODO("Not yet implemented")
        }

        delete("{id}") {
            val id = call.parameters["id"] ?: throw RuntimeException("ID required")
            TODO("Not yet implemented")
        }
    }
}