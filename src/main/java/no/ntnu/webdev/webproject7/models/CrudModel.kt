package no.ntnu.webdev.webproject7.models

import javax.persistence.MappedSuperclass

@MappedSuperclass
interface CrudModel<ID> {
    val id: ID?
    fun validate(): Boolean;
}
