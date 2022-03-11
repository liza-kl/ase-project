package de.dhbw.ka.datatables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object MemberTable : Table("Members") {
    val id: Column<Int> = integer("id").uniqueIndex().autoIncrement()
    val firstName: Column<String> = varchar("firstname", 50)
    val lastName: Column<String> = varchar("lastname",50)
    val memberStatus: Column<String> = varchar("memberstatus",50)
    override val primaryKey = PrimaryKey(id, name = "PK_MemberTable_Id")
}
