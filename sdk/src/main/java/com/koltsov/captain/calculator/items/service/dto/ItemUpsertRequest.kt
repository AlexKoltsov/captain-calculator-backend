package com.koltsov.captain.calculator.items.service.dto

data class ItemUpsertRequest(
    val name: String,
    val description: String?,
    val state: ItemState,
    val types: List<ItemType>
)