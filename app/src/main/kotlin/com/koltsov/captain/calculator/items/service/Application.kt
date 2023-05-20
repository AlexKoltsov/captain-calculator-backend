package com.koltsov.captain.calculator.items.service

import com.koltsov.captain.calculator.items.service.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureDI()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
