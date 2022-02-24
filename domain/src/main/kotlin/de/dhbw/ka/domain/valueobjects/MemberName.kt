package de.dhbw.ka.domain.valueobjects

data class MemberName(
    val firstName: String,
    val lastName: String
) {
    init {
        if(firstName.isEmpty() || lastName.isEmpty()) {
            throw Exception("Name must not be empty")
        }
    }
}
