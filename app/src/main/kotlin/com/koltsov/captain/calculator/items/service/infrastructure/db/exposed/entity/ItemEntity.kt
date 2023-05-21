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
    val name = varchar("name", 64)
    val description = text("description").nullable()
    val state = enumerationByName<ItemState>("state", 64)
    val imageId = reference("image_id", ImagesTable)
    // FIXME: types
}

class ItemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ItemEntity>(ItemsTable)

    var name: String by ItemsTable.name
    var description: String? by ItemsTable.description
    var state: ItemState by ItemsTable.state
    var image by ImageEntity referencedOn ItemsTable.imageId
}

fun ItemEntity.toDomain(): Item = Item(
    id = this.id.value,
    name = this.name,
    description = this.description,
    state = this.state,
    types = mutableListOf(), // TODO
    image = this.image.toDomain()
)