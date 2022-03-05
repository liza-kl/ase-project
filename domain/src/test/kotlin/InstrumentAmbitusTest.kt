import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest


class InstrumentAmbitusTest : FunSpec({

    test("checkAmbitus should throw Exception for tones longer than 2 chars") {
        val exception = shouldThrow<Exception> {
            InstrumentAmbitus("c123", "d2")
        }
        exception.message shouldBe("The tone you've provided does not exist (yet) or isn't a valid tone")
    }

    test("checkAmbitus should throw Exception bc first tone can't be higher than the last one") {
        val exception = shouldThrow<Exception> {
            InstrumentAmbitus("A", "D")
        }
        exception.message shouldBe("The first tone can't be higher or the same as the last one")
    }
    test("checkAmbitus should throw Exception bc first tone can't be the same than the last one") {
        val exception = shouldThrow<Exception> {
            InstrumentAmbitus("A", "A")
        }
        exception.message shouldBe("The first tone can't be higher or the same as the last one")
    }
    test("second") {
        val exception = shouldThrow<Exception> {
            InstrumentAmbitus("c2", "c1")
        }
        exception.message shouldBe("The first tone can't be higher than the last one")
    }

    test("Generated instrumentAmbitus should be c1 - c2") {
        val ambitus = InstrumentAmbitus("c1", "c2")
        ambitus.generatedAmbitus shouldBe "c1 - c2"
    }

}
)
