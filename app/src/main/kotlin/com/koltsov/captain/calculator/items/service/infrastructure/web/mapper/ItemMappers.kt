package com.koltsov.captain.calculator.items.service.infrastructure.web.mapper

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCaseCommand
import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCaseCommand
import com.koltsov.captain.calculator.items.service.dto.ItemResponse
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.dto.ItemsSearchRequest

fun ItemsSearchRequest.toUseCaseCommand(): FindItemsUseCaseCommand = FindItemsUseCaseCommand(
    name = name,
    description = description,
)

fun ItemUpsertRequest.toUseCaseCommand(): CreateItemUseCaseCommand = CreateItemUseCaseCommand(
    name = name,
    description = description,
)

fun fromDomain(item: Item): ItemResponse = with(item) {
    ItemResponse(id = id, name = name, description = description)
}