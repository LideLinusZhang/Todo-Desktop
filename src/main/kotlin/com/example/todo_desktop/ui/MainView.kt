package com.example.todo_desktop.ui
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.swing.text.html.ListView

class MainView: View() {
    //val ToDoListView: ToDoListView by inject<ToDoListView>()

    override val root : BorderPane by fxml("main_view.fxml")

    val toDoList : ListView by fxid("toDo_content_list")

    val myList : com.example.todo_desktop.ui.ToDoListView by inject()

    val subjectList : SubjectListView by inject()

    init {
        root.setPrefSize(1200.0, 720.0)
        root.center = myList.root
        root.left = subjectList.root
        title = "Anywhere ToDo"
    }
}



