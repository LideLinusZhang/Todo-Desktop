package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.service.RunCommandService
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import tornadofx.*
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


class MainApp: App(MainView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun main() {
    val mRunCommandService = RunCommandService()

    val commands = arrayOf("git", "status")
    /*
    var fileContent: MutableList<String> = mutableListOf<String>()
    var file = File(Paths.get("").toAbsolutePath().toString()+"/config.json")
    file.createNewFile()
    fileContent.add("{")
    fileContent.add("\"enabled\": true,")
    fileContent.add("\"serverUrl\": \"http://4.205.20.211\",")
    fileContent.add("\"userName\": \"test_account4\",")
    fileContent.add("\"password\": \"123456\"")
    fileContent.add("}")
    fileContent.add("}")
    Files.write(Paths.get("./bin/config.json"), fileContent, StandardCharsets.UTF_8)

     */

    //println(mRunCommandService.runCommand(commands))
    launch<MainApp>()
}
