package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object LentInstrumentsTable : Table() {
    private val lentId: Column<Int> = integer("lentId").uniqueIndex().autoIncrement()
    val memberId: Column<Int> = integer("memberId")
    val instrumentIdentification: Column<String> = varchar("instrumentIdentification",150)
    override val primaryKey = PrimaryKey(lentId, name = "PK_LentInstruments_Id")
}