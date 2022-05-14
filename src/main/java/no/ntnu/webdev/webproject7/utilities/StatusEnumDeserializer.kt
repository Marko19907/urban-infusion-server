package no.ntnu.webdev.webproject7.utilities

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
        val orderString: String = node.asText();

        return when (orderString.lowercase()) {
            "idle" -> OrderStatus.IDLE;
            "processing" -> OrderStatus.PROCESSING;
            "sent" -> OrderStatus.SENT;
            "delivered" -> OrderStatus.DELIVERED;
            else -> {
                throw IllegalArgumentException("Can not deserialize the status!");
            }
        }
    }
}
