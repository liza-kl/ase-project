package de.dhbw.ka.domain.repository

interface Repository {
    interface Repository<Entity : Any, ID : Any> {
        suspend fun findById(id: ID): Entity?
        suspend fun findAll(): List<Entity>
        suspend fun create(entity: Entity): Entity
        suspend fun update(entity: Entity): Entity
        suspend fun delete(id: ID)
    }
}
