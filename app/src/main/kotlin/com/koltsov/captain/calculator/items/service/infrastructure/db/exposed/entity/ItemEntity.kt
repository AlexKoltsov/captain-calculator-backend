package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity

import com.koltsov.captain.calculator.items.service.domain.model.Item
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object ItemsTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val name = varchar("name", 64).index()
    val description = text("description").nullable()
}

class ItemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ItemEntity>(ItemsTable)

    var name by ItemsTable.name
    var description by ItemsTable.description
}

fun ItemEntity.toItem(): Item = Item(
    id = this.id.value,
    name = this.name,
    description = this.description
)
