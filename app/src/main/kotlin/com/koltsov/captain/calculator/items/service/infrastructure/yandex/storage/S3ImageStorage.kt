package com.koltsov.captain.calculator.items.service.infrastructure.yandex.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.koltsov.captain.calculator.items.service.domain.model.Image
import com.koltsov.captain.calculator.items.service.domain.port.out.ImageStorage
import org.slf4j.LoggerFactory
import java.util.*

class S3ImageStorage(
    private val bucket: String,
    private val s3: AmazonS3,
) : ImageStorage {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun load(imageUrl: String): Image {
        return Image(
            id = UUID.randomUUID(), // FIXME:
            fileName = s3.getObject(bucket, imageUrl).key,
            url = imageUrl,
        )
    }

    override fun upload(fileName: String, imageData: ByteArray): String {
        s3.putObject(bucket, fileName, imageData.inputStream(), ObjectMetadata())
        return fileName
    }
}