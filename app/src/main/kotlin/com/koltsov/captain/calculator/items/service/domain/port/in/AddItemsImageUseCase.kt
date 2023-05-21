package com.koltsov.captain.calculator.items.service.domain.port.`in`

import java.util.*

interface AddItemsImageUseCase {
    fun addItemsImage(command: AddItemsImageUseCaseCommand)
}

data class AddItemsImageUseCaseCommand(
    val id: UUID,
    val imageName: String,
    val imageData: ByteArray,
)
