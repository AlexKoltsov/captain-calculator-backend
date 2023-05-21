package com.koltsov.captain.calculator.items.service.infrastructure.web.mapper

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCaseCommand
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCaseCommand
import com.koltsov.captain.calculator.items.service.dto.*
import com.koltsov.captain.calculator.items.service.domain.model.ItemState as DomainItemState
import com.koltsov.captain.calculator.items.service.domain.model.ItemType as DomainItemType

fun ItemsSearchRequest.toUseCaseCommand(): FindItemsUseCaseCommand = FindItemsUseCaseCommand(
    name = name,
    description = description,
)

fun ItemUpsertRequest.toUseCaseCommand(): CreateItemUseCaseCommand = CreateItemUseCaseCommand(
    name = name,
    state = state.toDomain(),
    types = types.map { it.toDomain() },
    description = description,
    imageUrl = imageUrl,
)

fun fromDomain(item: Item): ItemResponse = with(item) {
    ItemResponse(
        id = id,
        name = name,
        description = description,
        state = state.toResponse(),
        imageUrl = image?.url
    )
}

fun ItemState.toDomain(): DomainItemState = when (this) {
    ItemState.LOOSE -> DomainItemState.LOOSE
    ItemState.MOLTEN -> DomainItemState.MOLTEN
    ItemState.UNIT -> DomainItemState.UNIT
    ItemState.FLUID -> DomainItemState.FLUID
}

fun DomainItemState.toResponse(): ItemState = when (this) {
    DomainItemState.LOOSE -> ItemState.LOOSE
    DomainItemState.MOLTEN -> ItemState.MOLTEN
    DomainItemState.UNIT -> ItemState.UNIT
    DomainItemState.FLUID -> ItemState.FLUID
}

fun ItemType.toDomain(): DomainItemType = when (this) {
    ItemType.NATURAL_RESOURCE -> DomainItemType.NATURAL_RESOURCE
    ItemType.CRAFTED_MATERIAL -> DomainItemType.CRAFTED_MATERIAL
    ItemType.FOOD -> DomainItemType.FOOD
    ItemType.POLLUTION -> DomainItemType.POLLUTION
}