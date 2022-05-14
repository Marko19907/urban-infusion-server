package no.ntnu.webdev.webproject7.utilities


class FileUtilities {
}

fun loadImage(imageName: String?, extension: String?): ByteArray? {
    if (imageName == null || extension == null) {
        return null;
    }
    val inputStream = FileUtilities::class.java.classLoader.getResourceAsStream("product-images/$imageName.$extension");
    return inputStream?.readAllBytes();
}
