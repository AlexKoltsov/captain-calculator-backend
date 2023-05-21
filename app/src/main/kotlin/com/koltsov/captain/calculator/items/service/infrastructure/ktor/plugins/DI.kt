package com.koltsov.captain.calculator.items.service.infrastructure.ktor.plugins

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.koltsov.captain.calculator.items.service.api.AdminApi
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import com.koltsov.captain.calculator.items.service.domain.service.ItemsService
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out.ItemsRepositoryImpl
import com.koltsov.captain.calculator.items.service.infrastructure.web.adapter.out.ItemsController
import com.koltsov.captain.calculator.items.service.infrastructure.yandex.storage.S3ImageStorage
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import org.koin.ktor.plugin.koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    koin {
        slf4jLogger(level = Level.INFO)
        modules(
            commonModules(),
            itemModules(),
        )
    }
}

fun commonModules() = module {
    single {
        AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    "storage.yandexcloud.net", "ru-central1"
                )
            )
            .build()
    }
    single {
        named("s3-bucket")
        "captain-calculator"
    }

}

fun itemModules() = module {
    singleOf(::ItemsController) bind AdminApi::class
    singleOf(::ItemsService) binds arrayOf(FindItemsUseCase::class, CreateItemUseCase::class)
    singleOf(::S3ImageStorage) { named("s3-bucket") } bind ImageStorage::class
    singleOf(::ItemsRepositoryImpl) bind ItemsRepository::class
}
