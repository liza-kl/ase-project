package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object LentInstrumentsTable : Table() {
    val lentingId: Column<Int> = integer("lentId").uniqueIndex().autoIncrement()
    val memberId: Column<Int> = integer("memberId")
    val instrumentType: Column<String> = varchar("instrumentType", 150)
    val instrumentSerialNumber: Column<String> = varchar("instrumentSerialNumber",50)
    val instrumentManufacturer: Column<String> = varchar("instrumentManufacturer",50)
    override val primaryKey = PrimaryKey(lentingId, name = "PK_LentInstruments_Id")
}