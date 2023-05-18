package com.koltsov.captain.calculator.items.service.domain.port.out

import com.koltsov.captain.calculator.items.service.domain.model.Item

interface ItemsRepository {
    fun find(name: String? = null, description: String? = null): List<Item>
    fun save(item: Item): Item
}