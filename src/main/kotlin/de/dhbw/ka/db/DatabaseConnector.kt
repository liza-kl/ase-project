package de.dhbw.ka.db
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import io.github.cdimascio.dotenv.dotenv

class DatabaseConnector {
    private val dotenv = dotenv()
    fun connectToMusicSocietyDB() {
        try {
            Database.connect(
                "jdbc:postgresql://${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]}/${dotenv["DB_DATABASE"]}",
                driver = "org.postgresql.Driver",
                user = dotenv["DB_USER"],
                password = dotenv["DB_PASSWORD"]
            )
            println("Connection to Database was successful ")
        } catch (ex : ExposedSQLException) {
            println("Something went wrong with the Database Connection $ex")
        }
    }
}
