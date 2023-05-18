package com.koltsov.captain.calculator.items.service.domain.usecase

import com.koltsov.captain.calculator.items.service.domain.model.Item

interface FindItemsUseCase {
    fun findItems(command: FindItemsUseCaseCommand): List<Item>
}

interface CreateItemUseCase {
    fun create(command: CreateItemUseCaseCommand): Item
}