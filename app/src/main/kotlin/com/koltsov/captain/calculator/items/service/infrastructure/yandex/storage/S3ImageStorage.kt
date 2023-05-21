package com.koltsov.captain.calculator.items.service.infrastructure.yandex.storage

import com.amazonaws.services.s3.AmazonS3
import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import org.slf4j.LoggerFactory

class S3ImageStorage(
    private val bucket: String,
    private val s3: AmazonS3,
) : ImageStorage {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun load(imageUrl: String): Image {
        return Image(
            name = s3.getObject(bucket, imageUrl).key,
            url = imageUrl,
        )
    }
}