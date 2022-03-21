package de.dhbw.ka.storage.h2

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.datatables.RentalEntriesTable
import de.dhbw.ka.datatables.RentalInstrumentsTable
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.resultRowToInstrumentDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.resultRowToMemberDTO
import de.dhbw.ka.dto.RentalInstrumentEntryDTO.RentalInstrumentEntryMapper.resultRowToRentalInstrumentEntryDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private const val SAMPLE_SERIAL_NUMBER = "YHR-567D"
    private const val SAMPLE_MANUFACTURER = "Yamaha"
    private const val SAMPLE_INSTRUMENT_TYPE = "French Horn"

    fun init() {
        Database.connect(hikari())
        transaction {
            addLogger(StdOutSqlLogger)
            create(InstrumentTable)
            create(MemberTable)
            create(RentalEntriesTable)
            create(RentalInstrumentsTable)
            sampleMembers()
            sampleMusicInstruments()
            sampleInstrumentRentals()
            sampleRentalInstruments()
        }
    }

    private fun sampleRentalInstruments() {
        transaction {
            RentalInstrumentsTable.insert {
                it[instrumentType] = SAMPLE_INSTRUMENT_TYPE
                it[instrumentSerialNumber] = SAMPLE_SERIAL_NUMBER
                it[instrumentManufacturer] = SAMPLE_MANUFACTURER
                it[quantity] = 1
            }
        }
    }

    private fun sampleMembers() {
        transaction {
            MemberTable.insert {
                it[firstName] = "Marie"
                it[lastName] = "Maier"
                it[memberStatus] = "ACTIVE"
            } get MemberTable.id
            MemberTable.insert {
                it[firstName] = "Sophie"
                it[lastName] = "Müller"
                it[memberStatus] = "PASSIVE"
            }
            println("Sample Members: ${MemberTable.selectAll().map{ resultRowToMemberDTO(it) }}")
        }
    }

    private fun sampleMusicInstruments() {
        transaction {
            InstrumentTable.insert {
                it[instrumentType] = SAMPLE_INSTRUMENT_TYPE
                it[instrumentSerialNumber] = SAMPLE_INSTRUMENT_TYPE
                it[instrumentManufacturer] = SAMPLE_MANUFACTURER
                it[instrumentCategory] = "BRASS"
            }
        }
        println("Sample Instruments: ${InstrumentTable.selectAll().map{ resultRowToInstrumentDTO(it) }}")
    }

    private fun sampleInstrumentRentals() {
        transaction {
            RentalEntriesTable.insert {
                it[this.memberId] = 1
                it[this.instrumentType] = SAMPLE_INSTRUMENT_TYPE
                it[this.instrumentSerialNumber] = SAMPLE_INSTRUMENT_TYPE
                it[this.instrumentManufacturer] = SAMPLE_MANUFACTURER
            }
            println("Sample Rentals: ${RentalEntriesTable.selectAll().map{ resultRowToRentalInstrumentEntryDTO(it) }}")

        }
    }
    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:musikverwaltung"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}
