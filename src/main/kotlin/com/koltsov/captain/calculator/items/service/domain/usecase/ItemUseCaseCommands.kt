package com.koltsov.captain.calculator.items.service.domain.usecase

data class FindItemsUseCaseCommand(val name: String?, val description: String?)
data class CreateItemUseCaseCommand(val name: String, val description: String?)
