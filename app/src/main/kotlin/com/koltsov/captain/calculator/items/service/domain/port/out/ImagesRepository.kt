package com.koltsov.captain.calculator.items.service.domain.port.out

import com.koltsov.captain.calculator.items.service.domain.model.Image

interface ImagesRepository {
    fun save(image: Image): Image
}