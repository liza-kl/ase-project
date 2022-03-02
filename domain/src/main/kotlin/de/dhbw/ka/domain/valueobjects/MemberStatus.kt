package de.dhbw.ka.domain.valueobjects

enum class MemberStatusOptions { ACTIVE, PASSIVE }

@JvmInline
value class MemberStatus(private val status: String) {
    init {
        MemberStatusOptions.valueOf(status)
    }
}
