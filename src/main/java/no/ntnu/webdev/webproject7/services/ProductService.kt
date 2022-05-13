package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.ProductDTO
import no.ntnu.webdev.webproject7.dto.ProductUpdateDTO
import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.utilities.ProductHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val productHelper: ProductHelper
) : CrudService<Product, ProductId>(productRepository) {

    override fun delete(id: ProductId): Boolean {
        return this.productHelper.deleteProduct(id);
    }

    fun add(productDTO: ProductDTO?): Boolean {
        if (productDTO == null || !productDTO.validate()) {
            return false;
        }

        val product = Product(
            mutableListOf(),
            productDTO.price,
            productDTO.discount,
            null,
            productDTO.title,
            productDTO.description,
            productDTO.weight,
            productDTO.category
        );
        return super.add(product);
    }

    fun update(productUpdateDTO: ProductUpdateDTO?): Boolean {
        if (productUpdateDTO == null || !productUpdateDTO.validate()) {
            return false;
        }

        val product = this.productRepository.findByIdOrNull(productUpdateDTO.productId) ?: return false;

        product.title = productUpdateDTO.title;
        product.description = productUpdateDTO.description;
        product.price = productUpdateDTO.price;
        product.discount = productUpdateDTO.discount;
        product.weight = productUpdateDTO.weight;
        product.category = productUpdateDTO.category;

        return super.update(product);
    }

    fun getCategoryMap(): MutableSet<Category> {
        return Category.values().toHashSet();
    }

    fun getByCategory(category: String): List<Product> {
        return this.all().stream()
            .filter { it.category!!.name.lowercase() == category }
            .toList();
    }
}
