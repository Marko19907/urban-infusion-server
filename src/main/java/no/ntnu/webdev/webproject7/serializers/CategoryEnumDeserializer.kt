package no.ntnu.webdev.webproject7.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import no.ntnu.webdev.webproject7.models.Category
import java.io.IOException

/**
 * Deserializes a String into a Category.
 */
class CategoryEnumDeserializer : StdDeserializer<Category>(Category::class.java) {

    @Throws(IOException::class, JsonProcessingException::class, IllegalArgumentException::class)
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?): Category {
        val node: JsonNode = jsonParser.codec.readTree(jsonParser);
        val categoryString: String = node.asText();

        return when (categoryString.lowercase()) {
            "tea" -> Category.TEA;
            "accessories" -> Category.ACCESSORIES;
            else -> {
                throw IllegalArgumentException("Can not deserialize the category!");
            }
        }
    }
}
