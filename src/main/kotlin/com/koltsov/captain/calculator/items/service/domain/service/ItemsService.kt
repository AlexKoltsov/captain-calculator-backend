package com.koltsov.captain.calculator.items.service.domain.service

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCaseCommand
import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCaseCommand
import java.util.*

class ItemsService(private val itemsRepository: ItemsRepository) : FindItemsUseCase, CreateItemUseCase {

    override fun findItems(command: FindItemsUseCaseCommand): List<Item> {
        return itemsRepository.find(
            name = command.name,
            description = command.description,
        )
    }

    override fun create(command: CreateItemUseCaseCommand): Item {
        return with(command) {
            Item(
                id = UUID.randomUUID(),
                name = name,
                description = description,
            )
        }
            .let { itemsRepository.save(it) }
    }
}