package no.ntnu.webdev.webproject7.utilities

import java.util.*

fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        this.time = this@add;
        this.add(field, amount);
        return this.time;
    }
}
