package de.dhbw.ka.storage.h2

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.datatables.LentInstrumentsTable
import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.resultRowToInstrumentDTO
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrumentDTO
import de.dhbw.ka.dto.LentInstrumentDTO.LentInstrumentMapper.resultRowToLentInstrumentDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.resultRowToMemberDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.reflect.Member

object DatabaseFactory {
    fun init() {
        Database.connect(hikari())

        transaction {
            addLogger(StdOutSqlLogger)
            create(InstrumentTable)
            create(MemberTable)
            create(LentInstrumentsTable)
            sampleMembers()
            sampleMusicInstruments()
            sampleInstrumentRentals()
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
                it[instrumentType] = "French Horn"
                it[instrumentSerialNumber] = "YHR-567D"
                it[instrumentManufacturer] = "Yamaha"
                it[instrumentCategory] = "BRASS"
            } get InstrumentTable.storageId
        }
        println("Sample Instruments: ${InstrumentTable.selectAll().map{ resultRowToInstrumentDTO(it) }}")
    }

    private fun sampleInstrumentRentals() {
        transaction {
            LentInstrumentsTable.insert {
                it[this.memberId] = 1
                it[this.instrumentType] = "French Horn"
                it[this.instrumentSerialNumber] = "YHR-567D"
                it[this.instrumentManufacturer] = "Yamaha"
            }
            println("Sample Rentals: ${LentInstrumentsTable.selectAll().map{ resultRowToLentInstrumentDTO(it) }}")

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
