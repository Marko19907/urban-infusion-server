package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.dto.ProductDTO
import no.ntnu.webdev.webproject7.dto.ProductUpdatePartialDTO
import no.ntnu.webdev.webproject7.exceptions.ProductException
import no.ntnu.webdev.webproject7.models.Category
import no.ntnu.webdev.webproject7.models.MAX_DESCRIPTION_LENGTH
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val productDeletionService: ProductDeletionService
) : CrudService<Product, ProductId>(productRepository) {

    override fun delete(id: ProductId): Boolean {
        return this.productDeletionService.deleteProduct(id);
    }

    @Throws(ProductException::class)
    fun add(productDTO: ProductDTO?): Boolean {
        if (productDTO == null || !productDTO.validate()) {
            throw ProductException("The request is incorrectly formatted!");
        }
        this.verifyProductParams(productDTO.title, productDTO.price, productDTO.description);

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

    @Throws(ProductException::class)
    fun update(productUpdatePartialDTO: ProductUpdatePartialDTO?): Boolean {
        if (productUpdatePartialDTO == null || !productUpdatePartialDTO.validate()) {
            throw ProductException("The request is incorrectly formatted!");
        }
        this.verifyProductParams(
            productUpdatePartialDTO.title,
            productUpdatePartialDTO.price,
            productUpdatePartialDTO.description
        );

        val product = this.productRepository.findByIdOrNull(productUpdatePartialDTO.id)
            ?: throw ProductException("The product with id: ${productUpdatePartialDTO.id} does not exist!");

        product.title = productUpdatePartialDTO.title ?: product.title;
        product.description = productUpdatePartialDTO.description ?: product.description;
        product.price = productUpdatePartialDTO.price ?: product.price;
        product.discount = productUpdatePartialDTO.discount ?: product.discount;
        product.weight = productUpdatePartialDTO.weight ?: product.weight;
        product.category = productUpdatePartialDTO.category ?: product.category;

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

    @Throws(ProductException::class)
    private fun verifyProductParams(title: String?, price: Double?, description: String?) {
        if (title != null && title.isBlank()) {
            throw ProductException("The title field can not be empty!");
        }
        if (price != null) {
            if (price == 0.0) {
                throw ProductException("The price can not be 0!");
            }
            if (price < 0) {
                throw ProductException("The price can not be negative!");
            }
        }
        if (description != null && description.length > MAX_DESCRIPTION_LENGTH) {
            throw ProductException("The description can not be longer than $MAX_DESCRIPTION_LENGTH characters!");
        }
    }
}
