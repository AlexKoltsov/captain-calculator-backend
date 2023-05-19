package com.koltsov.captain.calculator.items.service.domain.port.`in`

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.model.ItemState
import com.koltsov.captain.calculator.items.service.domain.model.ItemType

interface CreateItemUseCase {
    fun create(command: CreateItemUseCaseCommand): Item
}

data class CreateItemUseCaseCommand(
    val name: String,
    val state: ItemState,
    val types: List<ItemType>,
    val description: String? = null,
    val imageUrl: String? = null,
)
