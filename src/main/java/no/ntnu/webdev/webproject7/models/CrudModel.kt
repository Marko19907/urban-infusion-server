package no.ntnu.webdev.webproject7.models

interface CrudModel<ID> {
    val id: ID?
    fun validate(): Boolean;
}