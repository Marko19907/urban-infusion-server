package no.ntnu.webdev.webproject7.utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class Utilities {

    /**
     * Private constructor to avoid object initialization.
     */
    private Utilities() {
    }

    /**
     * Converts the given Iterable to a List
     * @param iterable An Iterable collection to convert
     * @return List of the same type, empty list if the given iterable is null
     */
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        // Guard condition
        if (iterable == null) {
            return new LinkedList<>();
        }
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
