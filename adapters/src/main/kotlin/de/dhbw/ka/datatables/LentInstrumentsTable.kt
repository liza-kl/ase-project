package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object LentInstrumentsTable : Table() {
    val rentalId: Column<Int> = integer("id").uniqueIndex().autoIncrement()
    val memberId: Column<Int> = integer("memberid")
    val instrumentType: Column<String> = varchar("instrumenttype", 150)
    val instrumentSerialNumber: Column<String> = varchar("instrumentserialnumber",50)
    val instrumentManufacturer: Column<String> = varchar("instrumentmanufacturer",50)
    override val primaryKey = PrimaryKey(rentalId, name = "PK_LentInstruments_Id")
}