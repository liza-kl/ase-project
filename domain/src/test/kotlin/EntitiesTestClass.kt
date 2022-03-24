
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EntitiesTestClass: FunSpec({
    test("Should create a Member") {
        val createdMember = Member(1, MemberName("Max","Mustermann"),MemberStatus("ACTIVE"))
        createdMember.memberId shouldBe 1
        createdMember.memberName.firstName shouldBe "Max"
        createdMember.memberName.lastName shouldBe "Mustermann"
        createdMember.memberStatus.status shouldBe "ACTIVE"
    }
})
