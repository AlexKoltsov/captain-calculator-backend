package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.adapter.out

import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.port.out.ImagesRepository
import com.koltsov.captain.calculator.items.service.infrastructure.db.exposed.ImagesTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class ImagesRepositoryImpl : ImagesRepository {

    override fun save(image: Image): Image = transaction {
        ImagesTable.insert {
            it[id] = image.id
            it[fileName] = image.fileName
            it[url] = image.url
        }
        image
    }
}