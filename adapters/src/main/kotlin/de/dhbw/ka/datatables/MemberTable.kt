package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object MemberTable : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 50)
    val status: Column<String> = varchar("status",50)
    override val primaryKey = PrimaryKey(id)
}
