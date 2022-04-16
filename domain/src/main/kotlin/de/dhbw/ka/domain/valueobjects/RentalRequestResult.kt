package de.dhbw.ka.domain.valueobjects

data class RentalRequestResult(
    val approved: Boolean,
    val reasonsOfDenial: List<String>
)
