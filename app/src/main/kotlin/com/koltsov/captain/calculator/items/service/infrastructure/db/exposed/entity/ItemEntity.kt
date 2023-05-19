package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity

import com.koltsov.captain.calculator.items.service.domain.model.Item
import com.koltsov.captain.calculator.items.service.domain.model.ItemState
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object ItemsTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val name = varchar("name", 64).index()
    val description = text("description").nullable()
    val state = varchar("state", 64)

    // FIXME: types
//    val image = reference("image", ImagesTable)
}

class ItemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ItemEntity>(ItemsTable)

    var name by ItemsTable.name
    var description by ItemsTable.description
    var state by ItemsTable.state
//    val image by ImageEntity referencedOn ItemsTable.image
}

fun ItemEntity.toDomain(): Item = Item(
    id = this.id.value,
    name = this.name,
    description = this.description,
    state = ItemState.valueOf(this.state),
    types = mutableListOf(), // TODO
    image = null, // TODO
)
