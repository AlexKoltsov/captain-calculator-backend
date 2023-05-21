package com.koltsov.captain.calculator.items.service.infrastructure.ktor.plugins

import com.koltsov.captain.calculator.items.service.infrastructure.web.adapter.out.itemsRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val log = LoggerFactory.getLogger(javaClass)

    install(AutoHeadResponse)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            log.info("Error", cause)
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    itemsRouting()
}
