package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object RentalInstrumentsTable : Table("RentalInstruments") {
    val instrumentManufacturer: Column<String> = RentalInstrumentsTable.varchar("instrumentmanufacturer", 50)
    val instrumentSerialNumber: Column<String> = RentalInstrumentsTable.varchar("instrumentserialnumber", 50)
    val instrumentType: Column<String> = RentalInstrumentsTable.varchar("instrumenttype", 150)
    val quantity: Column<Int> = RentalInstrumentsTable.integer("quantity")
    override val primaryKey =
        PrimaryKey(
            instrumentManufacturer,
            instrumentSerialNumber,
            instrumentType,
            name = "PK_RentalInstruments"
        )
}