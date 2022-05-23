package no.ntnu.webdev.webproject7.utilities


class FileUtilities {
}

fun loadProductImage(imageName: String?, extension: String?): ByteArray? {
    return loadImage("product-images", imageName, extension);
}

fun loadUserImage(imageName: String?, extension: String?): ByteArray? {
    return loadImage("user-images", imageName, extension);
}

private fun loadImage(path: String, imageName: String?, extension: String?): ByteArray? {
    if (imageName == null || extension == null) {
        return null;
    }
    val inputStream = FileUtilities::class.java.classLoader.getResourceAsStream("$path/$imageName.$extension");
    return inputStream?.readAllBytes();
}
