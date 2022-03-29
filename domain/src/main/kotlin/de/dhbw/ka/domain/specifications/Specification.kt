package de.dhbw.ka.domain.specifications

interface Specification<T> {
    fun isSatisfiedBy(condition: T) : Boolean
}
