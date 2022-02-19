package de.dhbw.ka.domain.valueobjects


data class MemberStatus(private val status: Int) {

    init {
        require(status == 1 || status == 0 ) { "Status must be 0 for passive or 1 for active " }
    }

    fun toInt(): Int {
        return status
    }

    override fun toString(): String {
        return "$status"
    }
}

fun String.toMemberStatus(): MemberStatus {
    return MemberStatus(this.toInt())
}
