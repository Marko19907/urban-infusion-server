package no.ntnu.webdev.webproject7.repositories

import no.ntnu.webdev.webproject7.models.CommentId
import no.ntnu.webdev.webproject7.models.Product
import no.ntnu.webdev.webproject7.models.ProductId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, ProductId> {

    fun findProductByCommentsId(id: CommentId): Product?;
}
