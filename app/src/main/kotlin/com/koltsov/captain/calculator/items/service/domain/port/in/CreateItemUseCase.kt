package com.koltsov.captain.calculator.items.service.domain.port.`in`

import com.koltsov.captain.calculator.items.service.domain.model.Item

interface CreateItemUseCase {
    fun create(command: CreateItemUseCaseCommand): Item
}

data class CreateItemUseCaseCommand(val name: String, val description: String?)
