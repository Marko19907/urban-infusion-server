package no.ntnu.webdev.webproject7.utilities

fun <T> iterableToList(iterable : Iterable<T>): List<T> {
    return iterable.toList();
}

/**
 * Returns true if at least one of the given elements is null
 */
fun <T> objectsNotNull(vararg input: T): Boolean {
    return !input.any { e -> e == null }
}