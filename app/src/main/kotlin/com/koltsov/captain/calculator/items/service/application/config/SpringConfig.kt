package com.koltsov.captain.calculator.items.service.application.config

import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import com.koltsov.captain.calculator.items.service.domain.service.ItemsService
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out.ItemsRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {

    @Bean
    fun itemsRepository(): ItemsRepository = ItemsRepositoryImpl()

    @Bean
    fun itemsService(itemsRepository: ItemsRepository): ItemsService = ItemsService(itemsRepository)
}