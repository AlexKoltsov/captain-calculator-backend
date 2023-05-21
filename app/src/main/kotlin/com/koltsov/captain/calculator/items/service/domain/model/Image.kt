package com.koltsov.captain.calculator.items.service.domain.model

import java.util.*

class Image(
    id: UUID,
    var fileName: String,
    var url: String,
) : Entity<UUID>(id)