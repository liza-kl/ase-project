package de.dhbw.ka.domain.entities

abstract class Member(val name: String, val memberStatus : MemberStatus) : Entity {
    override val id: Int
        get() = TODO("Not yet implemented")
}

enum class MemberStatus {
    ACTIVE,
    PASSIVE
}
