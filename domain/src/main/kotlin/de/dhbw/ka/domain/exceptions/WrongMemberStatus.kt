package de.dhbw.ka.domain.exceptions

class WrongMemberStatus : ExceptionStrategy<IllegalArgumentException> {
    override fun throwsException(): IllegalArgumentException {
        throw IllegalArgumentException("Unfortunately you have the wrong MemberStatus in order to rent an instrument")
    }
}
