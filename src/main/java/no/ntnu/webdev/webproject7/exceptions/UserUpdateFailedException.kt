package no.ntnu.webdev.webproject7.exceptions

/**
 * Indicates that updating the User entity has failed.
 */
class UserUpdateFailedException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
