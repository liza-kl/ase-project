package de.dhbw.ka.domain.entities

class MusicSocietyMember(val name: String, val playsInstrument: Boolean, val memberStatus : MemberStatus) : Entity {
    override val id: Int
        get() = TODO("Not yet implemented")
}

enum class MemberStatus {
    ACTIVE,
    PASSIVE
}
