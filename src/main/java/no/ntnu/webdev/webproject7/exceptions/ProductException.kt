package no.ntnu.webdev.webproject7.exceptions

/**
 * Used to indicate that creating or updating a Product has failed.
 */
class ProductException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
