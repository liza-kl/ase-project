package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object MemberTable : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val forename: Column<String> = varchar("forename", 50)
    val surname: Column<String> = varchar("surname",50)
    val memberStatus: Column<String> = varchar("memberStatus",50)
    override val primaryKey = PrimaryKey(id)
}
