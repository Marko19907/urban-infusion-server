package no.ntnu.webdev.webproject7.utilities

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Rounds a BigDecimal to 2 decimal places.
 */
class BigDecimalSerializer : JsonSerializer<BigDecimal>() {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: BigDecimal, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeString(value.setScale(2, RoundingMode.HALF_UP).toString())
    }
}
