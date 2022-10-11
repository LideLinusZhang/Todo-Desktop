package com.example.todo_desktop.ui
import javafx.scene.Parent
import tornadofx.*
import java.awt.Button
import java.awt.Label

class MainView: View() {
    val listView: ListView by inject<ListView>()

    override val root = borderpane {
        title = "Anywhere ToDo"
        setPrefSize(1200.0, 720.0)
        top = listView.root
    }
}

class ListView: View() {

    override val root = vbox {
        button("Click me") {  }
        label("Hello World") {  }
    }
}

