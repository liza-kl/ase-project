package de.dhbw.ka.domain.entities

class Member(val id: Int, val name: String, val memberStatus: String)  {

    init {
        require(name.length > 5) {
            "The name can't be less than 5 chars"
        }
    }
}

enum class MemberStatus {
    ACTIVE,
    PASSIVE
}
