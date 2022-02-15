import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus
import de.dhbw.ka.domain.valueobjects.checkFormalities
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength


class InstrumentAmbitusTest : FunSpec({

    test("checkAmbitus should throw Exception for tones longer than 2 chars") {
        val exception = shouldThrow<Exception> {
            InstrumentAmbitus("c123", "d2")
        }
        exception.message shouldBe("The tone you've provided does not exist (yet)")
    }

    test("checkAmbitus should throw Exception for non-existent tone") {
        val exception = shouldThrow<Exception> {
            checkFormalities("S","a3")
        }
        exception.message shouldBe("The tone you've provided does not exist (yet)")
    }

    test("checkAmbitus should throw Exception bc first tone can't be higher than the last one") {
        val exception = shouldThrow<Exception> {
            checkFormalities("A","D")
        }
        exception.message shouldBe("The first tone can't be higher than the last")
    }

    test("second") {
        val exception = shouldThrow<Exception> {
            checkFormalities("c2","c1")
        }
        exception.message shouldBe("Please check your octaves")
    }

    test("first and last tone should not exceed the length of 2 chars") {
        val ambitus = InstrumentAmbitus("c1","c21");
        ambitus.firstTone shouldHaveLength (2);
        ambitus.lastTone shouldHaveLength (2);
    }

    test("Generated ambitus should be c1 - c2") {
        val ambitus = InstrumentAmbitus("c1","c2");
        ambitus.generatedAmbitus shouldBe "c1 - c1"
    }

}
)
