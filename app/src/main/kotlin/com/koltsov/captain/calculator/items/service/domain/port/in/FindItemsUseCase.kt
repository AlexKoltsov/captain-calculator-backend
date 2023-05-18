package com.koltsov.captain.calculator.items.service.domain.port.`in`

import com.koltsov.captain.calculator.items.service.domain.model.Item

interface FindItemsUseCase {
    fun findItems(command: FindItemsUseCaseCommand): List<Item>
}

data class FindItemsUseCaseCommand(val name: String?, val description: String?)
