import de.dhbw.ka.domain.valueobjects.MemberStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MemberStatusTest : FunSpec( {
    test("Should throw Exception when creating invalid MemberStatus") {
        val exception = shouldThrow<Exception> {
        MemberStatus("NOTVALID")
    }
    exception.message shouldBe("Please provide a valid member status, either 'ACTIVE' or 'PASSIVE'")

    }
})