package de.dhbw.ka.domain.valueobjects

/* Value Object für Rental Request erstellen der je nachdem zu oder abgesagt wird */

data class RentalRequest(val isRequestPermitted : Boolean = false) {

}
