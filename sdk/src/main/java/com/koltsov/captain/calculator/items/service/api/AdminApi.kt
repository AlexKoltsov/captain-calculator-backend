package com.koltsov.captain.calculator.items.service.api

import com.koltsov.captain.calculator.items.service.dto.ItemResponse
import com.koltsov.captain.calculator.items.service.dto.ItemUpsertRequest
import com.koltsov.captain.calculator.items.service.dto.ItemsSearchRequest
import java.util.*

interface AdminApi {
    fun searchItems(searchRequest: ItemsSearchRequest): List<ItemResponse>
    fun createItem(upsertRequest: ItemUpsertRequest): ItemResponse
    fun updateItem(id: UUID, upsertRequest: ItemUpsertRequest): ItemResponse
    fun deleteItem(id: UUID)
}