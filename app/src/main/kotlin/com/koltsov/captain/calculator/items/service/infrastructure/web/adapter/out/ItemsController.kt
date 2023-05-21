package com.koltsov.captain.calculator.items.service.infrastructure.web.adapter.out

import com.koltsov.captain.calculator.items.service.api.AdminApi
import com.koltsov.captain.calculator.items.service.domain.port.`in`.CreateItemUseCase
import com.koltsov.captain.calculator.items.service.domain.port.`in`.FindItemsUseCase
import com.koltsov.captain.calculator.items.service.dto.ItemResponse
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.dto.ItemsSearchRequest
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.fromDomain
import com.koltsov.captain.calculator.items.service.infrastructure.web.mapper.toUseCaseCommand
import java.util.*

class ItemsController(
    private val findItemsUseCase: FindItemsUseCase,
    private val createItemUseCase: CreateItemUseCase,
) : AdminApi {

    override fun searchItems(searchRequest: ItemsSearchRequest): List<ItemResponse> {
        return searchRequest.toUseCaseCommand()
            .let { findItemsUseCase.findItems(it) }
            .map { fromDomain(it) }
    }

    override fun createItem(upsertRequest: ItemUpsertRequest): ItemResponse {
        return upsertRequest.toUseCaseCommand()
            .let { createItemUseCase.create(it) }
            .let { fromDomain(it) }
    }

    override fun updateItem(
        id: UUID,
        upsertRequest: ItemUpsertRequest
    ): ItemResponse {
        TODO("Not yet implemented")
    }

    override fun deleteItem(id: UUID) {
        TODO("Not yet implemented")
    }
}