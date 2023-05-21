package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity

import com.koltsov.captain.calculator.items.service.domain.model.Item
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*
import com.koltsov.captain.calculator.items.service.domain.model.ItemState as DomainItemState

object ItemsTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val name = varchar("name", 64)
    val description = text("description").nullable()
    val state = enumerationByName<ItemState>("state", 64)

    // FIXME: types
//    val image = reference("image", ImagesTable)
}

class ItemEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ItemEntity>(ItemsTable)

    var name: String by ItemsTable.name
    var description: String? by ItemsTable.description
    var state: ItemState by ItemsTable.state
//    val image by ImageEntity referencedOn ItemsTable.image
}

enum class ItemState {
    LOOSE, MOLTEN, UNIT, FLUID,
}

fun ItemEntity.toDomain(): Item = Item(
    id = this.id.value,
    name = this.name,
    description = this.description,
    state = this.state.toDomain(),
    types = mutableListOf(), // TODO
    image = null, // TODO
)

fun ItemState.toDomain(): DomainItemState = when (this) {
    ItemState.LOOSE -> DomainItemState.LOOSE
    ItemState.MOLTEN -> DomainItemState.MOLTEN
    ItemState.UNIT -> DomainItemState.UNIT
    ItemState.FLUID -> DomainItemState.FLUID
}

fun DomainItemState.fromDomain(): ItemState = when (this) {
    DomainItemState.LOOSE -> ItemState.LOOSE
    DomainItemState.MOLTEN -> ItemState.MOLTEN
    DomainItemState.UNIT -> ItemState.UNIT
    DomainItemState.FLUID -> ItemState.FLUID
}
