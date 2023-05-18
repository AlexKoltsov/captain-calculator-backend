package com.koltsov.captain.calculator.items.service.infrastructure.web

import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.infrastructure.web.dto.*
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/items")
@RestController
class ItemsController(
    private val findItemsUseCase: FindItemsUseCase,
    private val createItemUseCase: CreateItemUseCase,
) {

    @GetMapping
    fun findItems(searchRequest: ItemsSearchRequest): List<ItemResponse> {
        return searchRequest.toUseCaseCommand()
            .let { findItemsUseCase.findItems(it) }
            .map { ItemResponse.fromDomain(it) }
    }

    @PostMapping
    fun createItem(@RequestBody itemUpsertRequest: ItemUpsertRequest): ItemResponse {
        return itemUpsertRequest.toUseCaseCommand()
            .let { createItemUseCase.create(it) }
            .let { ItemResponse.fromDomain(it) }
    }
}