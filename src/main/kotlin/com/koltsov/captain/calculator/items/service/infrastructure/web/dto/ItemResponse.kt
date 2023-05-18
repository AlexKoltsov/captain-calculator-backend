package com.koltsov.captain.calculator.items.service.infrastructure.web.dto

import com.koltsov.captain.calculator.items.service.domain.model.Item
import java.util.*

data class ItemResponse(
    val id: UUID,
    val name: String,
    val description: String?,
) {

    companion object {
        fun fromDomain(item: Item): ItemResponse = with(item) {
            ItemResponse(id = id, name = name, description = description)
        }
    }
}