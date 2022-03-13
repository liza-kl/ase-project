package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object RentalInstrumentsTable : Table("RentalInstruments") {
    val instrumentType: Column<String> = RentalInstrumentsTable.varchar("instrumenttype", 150)
    val instrumentSerialNumber: Column<String> = RentalInstrumentsTable.varchar("instrumentserialnumber", 50)
    val instrumentManufacturer: Column<String> = RentalInstrumentsTable.varchar("instrumentmanufacturer", 50)
    val quantity: Column<Int> = RentalInstrumentsTable.integer("quantity")
    override val primaryKey =
        PrimaryKey(
            instrumentType,
            instrumentManufacturer,
            instrumentSerialNumber, name = "PK_RentalInstruments"
        )
}