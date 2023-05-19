package com.koltsov.captain.calculator.items.service.domain.service

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCaseCommand
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCaseCommand
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import java.util.*

class ItemsService(
    private val itemsRepository: ItemsRepository,
    private val imageStorage: ImageStorage,
) : FindItemsUseCase, CreateItemUseCase {

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
                state = state,
                types = types.toMutableList(),
                image = imageUrl?.let { imageStorage.load(it) }
            )
        }
            .let { itemsRepository.save(it) }
    }
}