package com.koltsov.captain.calculator.items.service.infrastructure.web.dto

import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCaseCommand

data class ItemUpsertRequest(
    val name: String,
    val description: String?
)

fun ItemUpsertRequest.toUseCaseCommand(): CreateItemUseCaseCommand = CreateItemUseCaseCommand(
    name = name,
    description = description,
)