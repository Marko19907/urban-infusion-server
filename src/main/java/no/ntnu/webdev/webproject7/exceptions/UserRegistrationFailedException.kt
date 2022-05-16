package no.ntnu.webdev.webproject7.exceptions

/**
 * Indicates that the User creation has failed.
 */
class UserRegistrationFailedException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
