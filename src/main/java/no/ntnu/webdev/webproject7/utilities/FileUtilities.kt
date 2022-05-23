package no.ntnu.webdev.webproject7.utilities


class FileUtilities {
}

fun loadProductImage(imageName: String?, extension: String?): ByteArray? {
    if (imageName == null || extension == null) {
        return null;
    }
    val inputStream = FileUtilities::class.java.classLoader.getResourceAsStream("product-images/$imageName.$extension");
    return inputStream?.readAllBytes();
}

fun loadUserImage(imageName: String?, extension: String?): ByteArray? {
    if (imageName == null || extension == null) {
        return null;
    }
    val inputStream = FileUtilities::class.java.classLoader.getResourceAsStream("user-images/$imageName.$extension");
    return inputStream?.readAllBytes();
}
