package de.dhbw.ka.domain.exceptions

interface ExceptionStrategy<ExceptionType> {
    fun throwsException() : ExceptionType
}
