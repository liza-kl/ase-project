package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object InstrumentTable : Table("Instruments") {
    val instrumentType: Column<String> = varchar("instrumentType", 150)
    val instrumentManufacturer: Column<String> = varchar("instrumentManufacturer", 50)
    val instrumentSerialNumber: Column<String> = varchar("instrumentSerialNumber", 50)
    val instrumentCategory: Column<String> = varchar("instrumentCategory", 50)
    override val primaryKey =
        PrimaryKey(instrumentType, instrumentManufacturer, instrumentSerialNumber, name = "PK_Instruments")
}