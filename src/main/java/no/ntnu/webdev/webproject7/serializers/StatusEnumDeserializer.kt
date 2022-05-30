package no.ntnu.webdev.webproject7.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import no.ntnu.webdev.webproject7.models.OrderStatus
import java.io.IOException

/**
 * Deserializes a String into an OrderStatus.
 */
class StatusEnumDeserializer : StdDeserializer<OrderStatus>(OrderStatus::class.java) {

    @Throws(IOException::class, JsonProcessingException::class, IllegalArgumentException::class)
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?): OrderStatus {
        val node: JsonNode = jsonParser.codec.readTree(jsonParser);
        val orderStatusString: String = node.asText().lowercase();

        return OrderStatus.values()
            .toList()
            .stream()
            .filter { it.name.lowercase() == orderStatusString }
            .findFirst()
            .orElseThrow { IllegalArgumentException("Can not deserialize the status!") };
    }
}
