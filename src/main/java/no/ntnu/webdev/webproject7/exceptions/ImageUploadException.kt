package no.ntnu.webdev.webproject7.exceptions

/**
 * Captures that the image upload has failed.
 */
class ImageUploadException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
