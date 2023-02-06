package com.example.todo_desktop.service
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class ServerConfigUpdateService {
    val path = Paths.get("").toAbsolutePath().toString()

    val fileContent: MutableList<String> =

        ArrayList(Files.readAllLines(Paths.get(path + "/bin/config.json"), StandardCharsets.UTF_8))

    fun updateID(userID: SimpleStringProperty) {
        fileContent[4] = "\t\t\"userName\": \"" + userID.getValue() + "\","
        Files.write(Paths.get("./bin/config.json"), fileContent, StandardCharsets.UTF_8)
    }

    fun updatePassword(password: SimpleStringProperty) {
        fileContent[5] = "\t\t\"password\": \"" + password.getValue() + "\""
        Files.write(Paths.get("./bin/config.json"), fileContent, StandardCharsets.UTF_8)
    }

    fun updateCredential(userID: SimpleStringProperty, password: SimpleStringProperty) {
        println(path)
        fileContent[4] = "\t\t\"userName\": \"" + userID.getValue() + "\","
        fileContent[5] = "\t\t\"password\": \"" + password.getValue() + "\""
        Files.write(Paths.get("./bin/config.json"), fileContent, StandardCharsets.UTF_8)
    }
}