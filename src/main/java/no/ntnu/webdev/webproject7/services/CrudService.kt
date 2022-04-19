package no.ntnu.webdev.webproject7.services

import no.ntnu.webdev.webproject7.models.CrudModel
import org.springframework.data.repository.CrudRepository

open class CrudService<EntityType : CrudModel<ID>, ID>(
    private val repository: CrudRepository<EntityType, ID>
) {
    fun all(): List<EntityType> {
        return this.repository.findAll().toList();
    }

    fun getById(id: ID?): EntityType? {
        if (id == null) return null;
        return this.repository.findById(id).orElse(null);
    }

    fun add(entity: EntityType): Boolean {
        if (!entity.validate() || this.getById(entity.id) != null) return false
        return this.repository.save(entity).id == entity.id
    }

    fun update(entity: EntityType): Boolean {
        if (!entity.validate()) return false;
        this.repository.save(entity);
        return true;
    }

    fun delete(id: ID): Boolean {
        this.repository.deleteById(id)
        return this.getById(id) == null
    }
}
