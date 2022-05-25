package no.ntnu.webdev.webproject7.utilities

import com.thedeanda.lorem.LoremIpsum
import no.ntnu.webdev.webproject7.models.*
import no.ntnu.webdev.webproject7.repositories.CommentRepository
import no.ntnu.webdev.webproject7.repositories.OrderRepository
import no.ntnu.webdev.webproject7.repositories.OrdersProductsRepository
import no.ntnu.webdev.webproject7.repositories.ProductImageRepository
import no.ntnu.webdev.webproject7.repositories.ProductRepository
import no.ntnu.webdev.webproject7.repositories.UserImageRepository
import no.ntnu.webdev.webproject7.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DummyDataInitializer(
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository,
    private val productImageRepository: ProductImageRepository,
    private val ordersProductsRepository: OrdersProductsRepository,
    private val userImageRepository: UserImageRepository
) : ApplicationListener<ApplicationReadyEvent> {

    private val logger: Logger = LoggerFactory.getLogger("DummyDataInitializer");

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        this.logger.info("Initializing test data...");

        // Check if all repositories are empty
        if (arrayOf(
                this.commentRepository,
                this.productRepository,
                this.userRepository,
                this.orderRepository,
                this.productImageRepository,
                this.ordersProductsRepository,
                this.userImageRepository
            ).any { it.count() > 0 }
        ) {
            this.logger.info("The database is not empty, did not initialize test data...");
            return;
        }

        val user1 = User("user@gmail.com", "user", hashPassword("user"), "Ålesund", "6008", "Vågavegen 29", "98765432");
        val user2 = User("admin@teashop.com", "admin", hashPassword("admin"), "Oslo", "0001", "Majorstuen 5", "98876543");
        user2.role = Role.ADMIN;
        val user3 = User("user@example.no", "other_user", hashPassword("987"), "Bergen", "5003", "Juvik 12", "43219876");

        val comment1 = Comment(user1, "Very nice", null);
        val comment2 = Comment(user2, "I love this product", LocalDate.now());
        val comment3 = Comment(user3, "This product sucks!", LocalDate.now());
        val comment4 = Comment(user1, this.getLoremIpsum(), LocalDate.of(2020, 12, 12));

        val product1 =
            Product(mutableListOf(comment1), 24.99, 0.00, 1, "Afternoon Mixture", "Afternoon's tea is a black Yunnan-tea, mixed with white Chinese tea. Contains hints of vanilla, pomegranate and pear.", "10oz", Category.TEA);
        val product2 =
            Product(mutableListOf(comment2), 19.99, 0.00, 2, "Orange Tea", "Chinese black tea with a fresh taste of orange.", "10oz", Category.TEA);
        val product3 =
            Product(mutableListOf(comment3, comment4), 39.99, 0.15, 3, "Creylon Silver Tip", "This tea is harvested only a few times each year (3-4 times), and is considered one of the best teas in the world. Ceylon Silver Tip is a rare white tea with a mild and sweet honey-like taste.", "10oz", Category.TEA);
        val product4 =
            Product(mutableListOf(), 4.99, 0.00, 4, "Tea Cup", "The elegant Urban Infusion™ tea cup. Do you need a boost in the morning? That's exactly what our cup is for! Now is the time to add this classic cup to your tea collection!", "6oz", Category.ACCESSORIES);
        val product5 =
            Product(mutableListOf(), 29.99, 0.00, 5, "Golden tea", "Want to feel golden again? The distinctive blend and it's creamy, velvet taste help create an infusion that is both calming and refreshing. Don't miss this \"golden\" opportunity!", "10oz", Category.TEA);
        val product6 =
            Product(mutableListOf(), 19.99, 0.00, 6, "Kenya Silver Tip", "Kenya Silver Tip is a white tea with a nice, natural spicy taste.", "10oz", Category.TEA);
        val product7 =
            Product(mutableListOf(), 19.99, 0.00, 7, "Nepal Silver Tip", "Nepal Silver tip is a white tea with a nice, natural mild taste.", "10oz", Category.TEA);
        val product8 =
            Product(mutableListOf(), 9.99, 0.00, 8, "Caribbean Star", "On the occasion of summer, we have developed this wonderful mixture, which brings the mind to heat and holidays. Caribbean Star is a green tea with the lovely taste of peach, apple and papaya, and is perfectly usable for ice tea.", "10oz", Category.TEA);
        val product9 =
            Product(mutableListOf(), 7.99, 0.00, 9, "Chun Mee", "Chun Mee is a strong green Chinese tea with a sharp taste.", "10oz", Category.TEA);
        val product10 =
            Product(mutableListOf(), 12.99, 0.00, 10, "Globe Tea", "Special green tea from China. Hand made green tea, bound by a marigold. ", "10oz", Category.TEA);
        val product11 =
            Product(mutableListOf(), 7.99, 0.00, 11, "Green Bamboo Tea", "Green Japanese tea, with papaya, mango, pineapple, ginger, coconut, rosebuds, cardamom and bamboo shoots. \n Very sweet and aromatic tea.", "5oz", Category.TEA);
        val product12 =
            Product(mutableListOf(), 4.99, 0.00, 12, "Assam Earl Grey", "Assam / Earl Grey mixture is a powerful Assam mixed with the traditional Earl Grey.", "5oz", Category.TEA);
        val product13 =
            Product(mutableListOf(), 4.99, 0.00, 13, "Baroness Mixture", "Baroness Mixture is a black Chinese Panyong tea, with a fresh citrus and bergamot flavor.", "5oz", Category.TEA);
        val product14 =
            Product(mutableListOf(), 7.99, 0.00, 14, "Boston Mixture", "Lovely Chinese Panyong tea mixed with Earl Grey.", "5oz", Category.TEA);
        val product15 =
            Product(mutableListOf(), 109.99, 0.00, 15, "Arare Gold Teapot", "Traditional Japanese style cast-iron teapot in gold color. 1200 ml", "1200ml", Category.POTS);
        val product16 =
            Product(mutableListOf(), 99.99, 0.00, 16, "Sakura Black Teapot", "Traditional Japanese style cast-iron teapot in black color. 1000 ml", "1000ml", Category.POTS);
        val product17 =
            Product(mutableListOf(), 44.99, 0.00, 17, "Bright Ivory Teapot", "Ivory white porcelain teapot, in the classic English style. 1100 ml", "1100ml", Category.POTS);
        val product18 =
            Product(mutableListOf(), 39.99, 0.00, 18, "Blue Senbiki Teapot", "Classy handmade iron teapot. 600 ml", "600ml", Category.POTS);
        val product19 =
            Product(mutableListOf(), 59.00, 0.00, 19, "Red Geisha Teapot", "Perfect for a cup of tea in the traditional Japanese style. 300 ml", "300ml", Category.POTS);
        val product20 =
            Product(mutableListOf(), 5.99, 0.00, 20, "Tea Ball Infuser", "An original gadget to infuse our tea leaves.", "", Category.ACCESSORIES);
        val product21 =
            Product(mutableListOf(), 10.99, 0.00, 21, "Tea Infuser", "Practical and resistant infuser." , "", Category.ACCESSORIES);
        val product22 =
            Product(mutableListOf(), 15.99, 0.00, 22, "Classic White Mug", "A classic white mug in porcelain. 200 ml", "200ml", Category.ACCESSORIES);
        val product23 =
            Product(mutableListOf(), 11.99, 0.00, 23, "Classic White Tea Cup", "Classic white tea cup in porcelain. Plate included. 150 ml.", "150ml", Category.ACCESSORIES);
        val product24 =
            Product(mutableListOf(), 9.99, 0.00, 24, "Copacabana Tea", "Copacabana is a sweet and fresh exotic mixture based on a black Chinese tea.", "5oz", Category.TEA);
        val product25 =
            Product(mutableListOf(), 5.99, 0.00, 25, "Daisy's Blend", "Daisy's Blend is a mixture put together on the occasion of Queen Margethe II's 40th anniversary.", "5oz", Category.TEA);
        val product26 =
            Product(mutableListOf(), 7.99, 0.00, 26, "Decaff Ceylon", "Big leaf Ceylon without caffeine.", "5oz", Category.TEA);
        val product27 =
            Product(mutableListOf(), 6.99, 0.00, 27, "Indian Chai", "A a fresh breath from India and Nepal. A strong tea mixed with Indian chai of cinnamon, orange and juniper.", "5oz", Category.TEA);
        val product28 =
            Product(mutableListOf(), 5.99, 0.00, 28, "Indian Morning Tea", "Indian Morning Tea is an aromatic powerful morning mixture with Assam and Darjeeling.", "5oz", Category.TEA);
        val product29 =
            Product(mutableListOf(), 8.99, 0.00, 29, "Cinnamon Tea", "Black Chinese Keemmun with a round spiced cinnamon taste.", "5oz", Category.TEA);
        val product30 =
            Product(mutableListOf(), 8.99, 0.00, 30, "Emperor Tea", "The biggest hit from Anhui in China. A black tea with a smooth taste.", "5oz", Category.TEA);


        val ordersProducts1 = OrdersProducts(product1, 2);
        val ordersProducts2 = OrdersProducts(product4, 1);
        val ordersProducts3 = OrdersProducts(product3, 2);
        val ordersProducts4 = OrdersProducts(product6, 3);
        val ordersProducts5 = OrdersProducts(product22, 1);

        val order1 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.RECEIVED, user1);
        val order2 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.PROCESSING, user1);
        val order3 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.DELIVERED, user1);
        val order4 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.SENT, user1);
        val order5 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.RECEIVED, user2);
        val order6 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.PROCESSING, user2);
        val order7 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.DELIVERED, user2);
        val order8 = Order(mutableListOf(ordersProducts1, ordersProducts2), OrderStatus.SENT, user2);
        val order9 = Order(mutableListOf(ordersProducts1, ordersProducts2, ordersProducts3, ordersProducts4,ordersProducts5), OrderStatus.RECEIVED, user1);

        val productImage1 = ProductImage(1 ,"1-AfternoonMixture", "png");
        val productImage2 = ProductImage(2 ,"2-OrangeTea", "png");
        val productImage3 = ProductImage(3 ,"3-CreylonSilverTip", "png");
        val productImage4 = ProductImage(4 ,"4-TeaCup", "png");
        val productImage5 = ProductImage(5 ,"5-GoldenTea", "png");
        val productImage6 = ProductImage(6 ,"6-KenyaSilverTip", "png");
        val productImage7 = ProductImage(7 ,"7-NepalSilverTip", "png");
        val productImage8 = ProductImage(8 ,"8-CaribbeanStar", "png");
        val productImage9 = ProductImage(9 ,"9-ChunMee", "png");
        val productImage10 = ProductImage(10 ,"10-GlobeTea", "png");
        val productImage11 = ProductImage(11 ,"11-GreenBamboo", "png");
        val productImage12 = ProductImage(12 ,"12-AssamEarlGrey", "png");
        val productImage13 = ProductImage(13 ,"13-BaronessMixture", "png");
        val productImage14 = ProductImage(14 ,"14-BostonMixture", "png");
        val productImage15 = ProductImage(15 ,"15-ArareGoldTeapot", "png");
        val productImage16 = ProductImage(16 ,"16-HamaSakuraBlackTeapot", "png");
        val productImage17 = ProductImage(17 ,"17-BrightIvoryTeapot", "png");
        val productImage18 = ProductImage(18 ,"18-BlueSenbikiTeapot", "png");
        val productImage19 = ProductImage(19 ,"19-RedGeishaTeapot", "png");
        val productImage20 = ProductImage(20 ,"20-TeaBall", "png");
        val productImage21 = ProductImage(21 ,"21-TeaInfuser", "png");
        val productImage22 = ProductImage(22 ,"22-WhiteMug", "png");
        val productImage23 = ProductImage(23 ,"23-WhiteTeaCup", "png");
        val productImage24 = ProductImage(24 ,"24-CopacabanaTea", "png");
        val productImage25 = ProductImage(25 ,"25-DaisysBlend", "png");
        val productImage26 = ProductImage(26 ,"26-DecaffCeylon", "png");
        val productImage27 = ProductImage(27 ,"27-IndianChai", "png");
        val productImage28 = ProductImage(28 ,"28-IndianMorningTea", "png");
        val productImage29 = ProductImage(29 ,"29-CinnamonTea", "png");
        val productImage30 = ProductImage(30 ,"30-EmperorTea", "png");

        val userImage1 = UserImage(1, "User1", "jpeg");

        val users = arrayOf(user1, user2, user3);
        val comments = arrayOf(comment1, comment2, comment3, comment4);
        val ordersProducts =  arrayOf(ordersProducts1, ordersProducts2, ordersProducts3);
        val products = arrayOf(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11,
            product12, product13, product14, product15, product16, product17, product18, product19, product20, product21, product22, product23,
            product24, product25, product26, product27, product28, product29, product30);
        val orders = arrayOf(order1, order2, order3, order4, order5, order6, order7, order8, order9);
        val productImages = arrayOf(productImage1, productImage2, productImage3, productImage4, productImage5, productImage6, productImage7, productImage8,
            productImage9, productImage10, productImage11, productImage12, productImage13, productImage14, productImage15, productImage16, productImage17, productImage18,
            productImage19, productImage20, productImage21, productImage22, productImage23, productImage24, productImage25, productImage26, productImage27, productImage28,
            productImage29, productImage30);
        val userImages = arrayOf(userImage1);

        users.forEach { this.userRepository.save(it) }
        products.forEach { this.productRepository.save(it) }
        comments.forEach { this.commentRepository.save(it) }
        ordersProducts.forEach { this.ordersProductsRepository.save(it) }
        orders.forEach { this.orderRepository.save(it) }
        productImages.forEach { this.productImageRepository.save(it) }
        userImages.forEach { this.userImageRepository.save(it) }

        this.logger.info("Test data initialized");
    }

    private fun getLoremIpsum(): String {
        return LoremIpsum.getInstance().getWords(20, 50);
    }
}
