package no.ntnu.webdev.webproject7.crud

import org.springframework.data.repository.CrudRepository

open class CrudService<EntityType : CrudModel<ID>, ID>(
    private val repository: CrudRepository<EntityType, ID>
) {
    fun all(): List<EntityType> {
        return repository.findAll().toList();
    }

    fun getById(id: ID?): EntityType? {
        if (id == null) return null;
        return repository.findById(id).orElse(null);
    }

    fun add(entity: EntityType): Boolean {
        if (entity.id == null || getById(entity.id) != null) return false
        return repository.save(entity).id == entity.id
    }

    fun update(entity: EntityType): Boolean {
        repository.save(entity);
        return true
    }

    fun delete(id: ID): Boolean {
        repository.deleteById(id)
        return getById(id) == null
    }
}