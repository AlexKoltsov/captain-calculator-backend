package com.koltsov.captain.calculator.items.service.domain.model

import java.math.BigDecimal
import java.util.*

class Recipe(
    id: UUID,
    val sourceItems: MutableList<ItemBag>,
    val targetItems: MutableList<ItemBag>,
    var transformationRate: BigDecimal,
) : Entity<UUID>(id) {
}