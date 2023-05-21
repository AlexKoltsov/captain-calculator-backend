package com.koltsov.captain.calculator.items.service.domain.service

import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.`in`.*
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import com.koltsov.captain.calculator.items.service.domain.port.out.ImagesRepository
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import java.util.*

class ItemsService(
    private val itemsRepository: ItemsRepository,
    private val imagesRepository: ImagesRepository,
    private val imageStorage: ImageStorage,
) : FindItemsUseCase, CreateItemUseCase, AddItemsImageUseCase {

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

    override fun addItemsImage(command: AddItemsImageUseCaseCommand) {
        val url = imageStorage.upload(command.imageName, command.imageData)
        val imageId = UUID.randomUUID()
        imagesRepository.save(
            Image(
                id = imageId,
                fileName = command.imageName,
                url = url
            )
        )
        itemsRepository.assignImage(command.id, imageId)
    }
}