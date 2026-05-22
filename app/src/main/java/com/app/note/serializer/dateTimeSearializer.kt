package com.app.note.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


object dateTimeSearializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.LONG)
        private val  zoneid = ZoneId.systemDefault()

    override fun serialize(
        encoder: Encoder,
        value: LocalDateTime
    ) {
        val ms =value.atZone(zoneid).toInstant().toEpochMilli()
        encoder.encodeLong(ms)
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val ms = decoder.decodeLong()
        return Instant.ofEpochMilli(ms).atZone(zoneid).toLocalDateTime()
    }
}