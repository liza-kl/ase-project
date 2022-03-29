
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.domain.valueobjects.MemberStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

class VOTestClass : FunSpec( {

    test("Should throw Exception when creating invalid MemberStatus") {
        val exception = shouldThrow<IllegalArgumentException> {
        MemberStatus("NOTVALID")
    }
    exception.message shouldStartWith ("No enum constant")
    }

    test("Should throw Exception when creating invalid InstrumentCategory") {
        val exception = shouldThrow<Exception> {
            InstrumentCategory("WOODWID")
        }
        exception.message shouldStartWith ("No enum constant")
    }

    test("Should throw Exception if at least one attribute is empty") {
        val exception = shouldThrow<IllegalArgumentException> {
            InstrumentIdentification("","YHR-567D","French Horn")
        }
        exception.message shouldBe("Please provide data for every property of the desired Instrument")
    }}

)
