package com.koltsov.captain.calculator.items.service.domain.model

import java.util.*

class Building(
    id: UUID,
    var name: String,
    var description: String?,
    var constructionCost: ItemBag,
    var workersCost: Int,
    var electricityCost: Int,
    var maintenanceCost: Int,
    var unityBoost: Boolean,
    val recipes: MutableList<Recipe>,
    var size: Size,
    var image: Image,
) : Entity<UUID>(id)