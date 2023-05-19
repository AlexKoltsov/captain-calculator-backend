package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.entity

import com.koltsov.captain.calculator.items.service.domain.model.Image
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object ImagesTable : IdTable<UUID>() {
    override val id = uuid("id").entityId()
    val fileName = varchar("file_name", 64).index()
    val url = varchar("url", 255)
}

class ImageEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ItemEntity>(ImagesTable)

    var fileName by ImagesTable.fileName
    var url by ImagesTable.url
}

fun ImageEntity.toDomain(): Image = Image(
    name = this.fileName,
    url = this.url
)
