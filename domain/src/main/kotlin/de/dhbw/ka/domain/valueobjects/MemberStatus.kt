package de.dhbw.ka.domain.valueobjects

enum class MemberStatusOptions { ACTIVE, PASSIVE }

@JvmInline
value class MemberStatus(val status: String) {
    init {
        require(MemberStatusOptions.valueOf(status).toString() == status) {
            throw IllegalArgumentException("Please provide a valid member status, either 'ACTIVE' or 'PASSIVE'")
        }
    }
}
