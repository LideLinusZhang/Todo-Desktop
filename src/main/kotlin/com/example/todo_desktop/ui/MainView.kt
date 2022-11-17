package com.example.todo_desktop.ui
import edu.uwaterloo.cs.todo.lib.TodoCategoryModel
import edu.uwaterloo.cs.todo.lib.deserializeCategoryList
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.BorderPane
import tornadofx.*
import javax.swing.text.html.ListView
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode
import java.io.File
import javafx.geometry.Pos.CENTER
import javax.swing.GroupLayout.Alignment

class MainView: View() {
    //val ToDoListView: ToDoListView by inject<ToDoListView>()

    val a = javaClass.getResourceAsStream("main_view.fxml")

    override val root : BorderPane by fxml(a)

    val toDoList : ListView by fxid("toDo_content_list")

    val myList : com.example.todo_desktop.ui.ToDoListView by inject()

    val subjectList : SubjectListView by inject()

    val headerView : HeaderView by inject()

    val userID = SimpleStringProperty()

    val password = SimpleStringProperty()
    init {
        root.setPrefSize(1200.0, 720.0)
        title = "Anywhere ToDo Login"
        root.left = hbox {
            vbox {
                setPrefSize(400.0, 720.0)
            }
            //alignment = CENTER
            vbox {
                setPrefSize(400.0, 720.0)
                vbox{
                    setPrefSize(400.0, 150.0)
                }
                form {
                    setPrefSize(400.0, 500.0)
                    fieldset {
                        field("User ID:") {
                            textfield(password) {
                                id = "myTextField"
                                text = "Enter your user ID"
                                setOnMouseClicked { text = "" }
                                setOnKeyPressed {
                                }
                            }
                        }
                        field("Password:") {

                            textfield(userID) {
                                id = "myTextField"
                                text = "Enter your password"
                                setOnMouseClicked { text = "" }
                                setOnKeyPressed {

                                }
                            }
                        }
                        hbox {
                            setPrefSize(400.0, 50.0)
                            alignment = CENTER
                            button("Login") {
                                setPrefSize(100.0, 30.0)
                                action {
                                    title = "Anywhere ToDo"
                                    root.top = headerView.root
                                    root.center = myList.root
                                    root.left = subjectList.root
                                }
                            }
                            vbox {
                                setPrefSize(20.0, 50.0)
                            }
                            button("Sign up") {
                                setPrefSize(100.0, 30.0)
                                action {
                                    title = "Anywhere ToDo"
                                    root.top = headerView.root
                                    root.center = myList.root
                                    root.left = subjectList.root
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



