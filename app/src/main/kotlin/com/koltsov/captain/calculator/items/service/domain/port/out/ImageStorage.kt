package com.koltsov.captain.calculator.items.service.domain.port.out

import com.koltsov.captain.calculator.items.service.domain.model.Image

interface ImageStorage {
    fun load(imageUrl: String): Image
}
