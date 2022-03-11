package de.dhbw.ka.storage.h2

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.datatables.LentInstrumentsTable
import de.dhbw.ka.datatables.MemberTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

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
                it[this.firstName] = "Marie"
                it[this.lastName] = "Maier"
                it[this.memberStatus] = "ACTIVE"
            }
            MemberTable.insert {
                it[this.firstName] = "Sophie"
                it[this.lastName] = "Müller"
                it[this.memberStatus] = "PASSIVE"
            }

        }
    }

    private fun sampleMusicInstruments() {
        transaction {
            InstrumentTable.insert {
                it[this.instrumentType] = "French Horn"
                it[this.instrumentSerialNumber] = "YHR-567D"
                it[this.instrumentManufacturer] = "Yamaha"
                it[this.instrumentCategory] = "BRASS"
            }
        }
    }

    private fun sampleInstrumentRentals() {
        transaction {
            LentInstrumentsTable.insert {
                it[this.memberId] = 1
                it[this.instrumentType] = "French Horn"
                it[this.instrumentSerialNumber] = "YHR-567D"
                it[this.instrumentManufacturer] = "Yamaha"
            }
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
