package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class ItemsRepositoryImpl : ItemsRepository {

    override fun findAll(): List<Item> = transaction {
        ItemEntity
            .all()
            .map { it.toItem() }
    }

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

private fun ItemEntity.toItem(): Item = Item(
    id = this.id.value,
    name = this.name,
    description = this.description
)

