
import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
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

    test("Should create an Instrument") {
        val createdInstrument = Instrument(InstrumentIdentification("Thomann","Little Bear","Bb-Tuba"),
            InstrumentCategory("BRASS"))
        createdInstrument.instrumentIdentification.instrumentType shouldBe "Bb-Tuba"
        createdInstrument.instrumentIdentification.instrumentSerialNumber shouldBe "Little Bear"
        createdInstrument.instrumentIdentification.instrumentManufacturer shouldBe "Thomann"
    }

    test("Should create a InstrumentRentalEntry") {
        val createdInstrumentRental = InstrumentRentalEntry(1,2,
            InstrumentIdentification("Yamaha",
            "YHR-752","French Horn"))
        createdInstrumentRental.rentalEntryId shouldBe 1
        createdInstrumentRental.memberId shouldBe 2
        createdInstrumentRental.instrumentIdentification shouldBe InstrumentIdentification(instrumentManufacturer="Yamaha", instrumentSerialNumber="YHR-752", instrumentType="French Horn")
    }
})
