package com.koltsov.captain.calculator.items.service.domain.model

import java.util.*

class Item(
    id: UUID,
    var name: String,
    var description: String?,
    var state: ItemState,
    val types: MutableList<ItemType>,
    var image: Image,
) : Entity<UUID>(id)

enum class ItemState {
    LOOSE, MOLTEN, UNIT, FLUID,
}

enum class ItemType {
    NATURAL_RESOURCE, CRAFTED_MATERIAL, FOOD, POLLUTION
}
