package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed

import com.koltsov.captain.calculator.items.service.domain.model.ItemState
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object ItemsTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val name = varchar("name", 64)
    val description = text("description").nullable()
    val state = enumerationByName<ItemState>("state", 64)
    val imageId = optReference("image_id", ImagesTable)
    // FIXME: types
}

object ImagesTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val fileName = varchar("file_name", 64)
    val url = varchar("url", 255)
}
