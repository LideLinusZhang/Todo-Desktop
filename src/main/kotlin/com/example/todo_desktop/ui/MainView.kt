package com.example.todo_desktop.ui
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.awt.Button
import java.awt.Label

class MainView: View() {
    //val listView: ListView by inject<ListView>()

    override val root : BorderPane by fxml("main_view.fxml")

    init {
        root.setPrefSize(1200.0, 720.0)
        title = "Anywhere ToDo"
    }
}



