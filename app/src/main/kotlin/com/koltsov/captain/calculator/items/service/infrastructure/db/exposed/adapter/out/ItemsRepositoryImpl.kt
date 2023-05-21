package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out

import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.port.out.ItemsRepository
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.ImagesTable
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.ItemsTable
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.regexpOp
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ItemsRepositoryImpl : ItemsRepository {

    override fun find(name: String?, description: String?): List<Item> = transaction {
        ItemsTable.join(ImagesTable, JoinType.LEFT, onColumn = ItemsTable.imageId, otherColumn = ImagesTable.id)
            .select {
                (ItemsTable.name.regexpOp(name)) and
                        (ItemsTable.description.regexpOp(description))
            }
            .map { it.item() }
    }

    override fun save(item: Item): Item = transaction {
        ItemsTable.insert {
            it[id] = UUID.randomUUID()
            it[name] = item.name
            it[description] = item.description
            it[state] = item.state
            item.image
                ?.let { image -> it[imageId] = EntityID(image.id, ImagesTable) }
        }
        item
    }
}

private fun ResultRow.item(): Item = Item(
    id = this[ItemsTable.id].value,
    name = this[ItemsTable.name],
    description = this[ItemsTable.description],
    state = this[ItemsTable.state],
    image = this.image(),
    types = mutableListOf(),
)

private fun ResultRow.image(): Image = Image(
    id = this[ImagesTable.id].value,
    fileName = this[ImagesTable.fileName],
    url = this[ImagesTable.url],
)



