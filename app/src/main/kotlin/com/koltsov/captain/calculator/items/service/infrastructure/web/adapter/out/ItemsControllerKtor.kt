package com.koltsov.captain.calculator.items.service.infrastructure.web.adapter.out

import com.koltsov.captain.calculator.items.service.domain.port.`in`.*
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.fromDomain
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.toUseCaseCommand
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import java.util.*

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

        route("{id}") {
            put {
                val id = call.parameters["id"].let { UUID.fromString(it) } ?: throw RuntimeException("ID required")
                TODO("Not yet implemented")
            }

            delete {
                val id = call.parameters["id"].let { UUID.fromString(it) } ?: throw RuntimeException("ID required")
                TODO("Not yet implemented")
            }

            post("image") {
                val addItemsImageUseCase: AddItemsImageUseCase by inject(AddItemsImageUseCase::class.java)
                val id = call.parameters["id"].let { UUID.fromString(it) } ?: throw RuntimeException("ID required")
                val (imageName, imageData) = call.receiveMultipart().readPart()
                    ?.let {
                        val pair = when (it) {
                            is PartData.FileItem -> it.originalFileName!! to it.streamProvider().readBytes()
                            else -> null
                        }
                        it.dispose
                        pair
                    }
                    ?: throw RuntimeException("image required")



                addItemsImageUseCase.addItemsImage(
                    AddItemsImageUseCaseCommand(
                        id = id,
                        imageName = imageName,
                        imageData = imageData
                    )
                )
            }
        }
    }
}