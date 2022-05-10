package no.ntnu.webdev.webproject7.utilities


class FileUtilities {
}

fun loadImage(imageName: String): ByteArray? {
    val inputStream = FileUtilities::class.java.classLoader.getResourceAsStream("product-images/$imageName");
    return inputStream?.readAllBytes();
}
