package com.koltsov.captain.calculator.items.service.dto

import java.util.*

data class ItemResponse(
    val id: UUID,
    val name: String,
    val description: String?,
) {
}