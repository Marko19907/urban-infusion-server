package no.ntnu.webdev.webproject7.utilities

/**
 * Returns true if at least one of the given elements is null
 */
fun <T> objectsNotNull(vararg input: T): Boolean {
    return input.all { it != null }
}
