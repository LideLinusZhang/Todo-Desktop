package com.example.todo_desktop.ui
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.awt.Button
import java.awt.Label
import javax.swing.text.html.ListView

class MainView: View() {
    //val listView: ListView by inject<ListView>()

    override val root : BorderPane by fxml("main_view.fxml")

    val toDoList : ListView by fxid("toDo_content_list")

    val myList : listView by inject()

    val subjectList : SubjectListView by inject()

    init {
        root.setPrefSize(1200.0, 720.0)
        root.center = myList.root
        root.left = subjectList.root
        title = "Anywhere ToDo"

    }
}



