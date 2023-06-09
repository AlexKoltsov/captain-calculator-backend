package com.koltsov.captain.calculator.items.service.plugins

import com.koltsov.captain.calculator.items.service.api.AdminApi
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import com.koltsov.captain.calculator.items.service.domain.service.ItemsService
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out.ItemsRepositoryImpl
import com.koltsov.captain.calculator.items.service.infrastructure.web.ItemsController
import com.koltsov.captain.calculator.items.service.infrastructure.yandex.storage.ImageStorageImpl
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import org.koin.ktor.plugin.koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    koin {
        slf4jLogger(level = Level.INFO)
        modules(
            itemModules()
        )
    }
}

fun itemModules() = module {
    singleOf(::ItemsController) bind AdminApi::class
    singleOf(::ItemsService) binds arrayOf(FindItemsUseCase::class, CreateItemUseCase::class)
    singleOf(::ImageStorageImpl) bind ImageStorage::class
    singleOf(::ItemsRepositoryImpl) bind ItemsRepositoryImpl::class
}
