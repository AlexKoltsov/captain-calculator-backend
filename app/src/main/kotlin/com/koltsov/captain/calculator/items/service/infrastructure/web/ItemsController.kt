package com.koltsov.captain.calculator.items.service.infrastructure.web

import com.koltsov.captain.calculator.items.service.api.AdminApi
import com.koltsov.captain.calculator.items.service.domain.usecase.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.usecase.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.dto.ItemResponse
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.dto.ItemsSearchRequest
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.fromDomain
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.toUseCaseCommand
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/items")
@RestController
class ItemsController(
    private val findItemsUseCase: FindItemsUseCase,
    private val createItemUseCase: CreateItemUseCase,
) : AdminApi {

    @GetMapping
    override fun searchItems(searchRequest: ItemsSearchRequest): List<ItemResponse> {
        return searchRequest.toUseCaseCommand()
            .let { findItemsUseCase.findItems(it) }
            .map { fromDomain(it) }
    }

    @PostMapping
    override fun createItem(@RequestBody upsertRequest: ItemUpsertRequest): ItemResponse {
        return upsertRequest.toUseCaseCommand()
            .let { createItemUseCase.create(it) }
            .let { fromDomain(it) }
    }

    @PutMapping("{id}")
    override fun updateItem(
        @PathVariable id: UUID,
        @RequestBody upsertRequest: ItemUpsertRequest
    ): ItemResponse {
        TODO("Not yet implemented")
    }

    @DeleteMapping("{id}")
    override fun deleteItem(@PathVariable id: UUID) {
        TODO("Not yet implemented")
    }
}