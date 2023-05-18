package com.koltsov.captain.calculator.items.service.infrastructure.web.dto

import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCaseCommand

data class ItemsSearchRequest(
    val name: String?,
    val description: String?
)

fun ItemsSearchRequest.toUseCaseCommand(): FindItemsUseCaseCommand = FindItemsUseCaseCommand(
    name = name,
    description = description,
)