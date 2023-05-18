package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity.ItemEntity
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity.ItemsTable
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity.toItem
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.regexpOp
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class ItemsRepositoryImpl : ItemsRepository {

    override fun find(name: String?, description: String?): List<Item> = transaction {
        ItemEntity
            .find {
                (ItemsTable.name.regexpOp(name)) and
                        (ItemsTable.description.regexpOp(description))
            }
            .map { it.toItem() }
    }

    override fun save(item: Item): Item = transaction {
        ItemEntity.new(id = item.id) {
            name = item.name
            description = item.description
        }
            .toItem()
    }
}

