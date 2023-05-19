package com.koltsov.captain.calculator.items.service.infrastructure.yandex.storage

import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import org.springframework.stereotype.Component

@Component
class ImageStorageImpl : ImageStorage {
    override fun load(imageUrl: String): Image {
        TODO("Not yet implemented")
    }
}