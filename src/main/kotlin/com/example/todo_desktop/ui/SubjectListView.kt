package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.controller.ListController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos.CENTER
import javafx.geometry.Pos.CENTER_RIGHT
import tornadofx.*
import com.example.todo_desktop.ui.ToDoListView
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

// Class definition for the list of
class SubjectListView : View("Subject List") {
    private var subjects = mutableListOf<String>().observable()
    val input = SimpleStringProperty()
    val curSubjectIndex = 0;
    val listController : ListController by inject()

    val mToDoListView : ToDoListView by inject()

    override val root = hbox {
        vbox {
            setPrefSize(10.0, 700.0)
        }
        vbox {
            vbox {
                alignment = CENTER
                label("SUBJECTS") {
                    style = "-fx-font: 20 arial;"
                    minWidth(50.0)
                    minHeight(50.0)
                }
            }
            listview(subjects) {
                setPrefSize(160.0, 500.0)

                /*
                onDoubleClick {
                    println("double click")
                    val selectedIdx = selectionModel.selectedIndices
                    subjects.removeAt(selectedIdx[0])
                }

                onUserSelect {
                    temp.records.add("Add item success")
                }*/

                onDoubleClick {
                    println("double click on subject list")
                    // First check if the user is clicking the current subject:

                    // Not clicking current branch -->
                    // Call CLI & search for tasks (with selectedItem as parameter)

                    // Delete all tasks in current list.
                    mToDoListView.records.removeAll(mToDoListView.records)

                    // Refill content from database's result
                    mToDoListView.records.add("add1")
                }

                setOnKeyPressed {
                    if (it.code.equals(KeyCode.W)) {
                        println("W key pressed on subject list")
                        val selectedIdx = selectionModel.selectedIndices[0]
                        if (selectedIdx != 0) {
                            val tmpString = subjects[selectedIdx - 1]
                            subjects.removeAt(selectedIdx - 1)
                            subjects.add(selectedIdx, tmpString)
                            println("Item switched up")
                        }
                    } else if (it.code.equals(KeyCode.S)) {
                        println("S key pressed on subject list")
                        val selectedIdx = selectionModel.selectedIndices[0]
                        if (selectedIdx != subjects.size-1) {
                            val tmpString = subjects[selectedIdx + 1]
                            subjects.add(selectedIdx, tmpString)
                            subjects.removeAt(selectedIdx + 2)
                            println("Item switched down")
                        }
                    }
                }
                cellFormat {
                    graphic = HBox().apply {
                        addClass(Styles.defaultSpacing)
                        label(it) {
                            setPrefWidth(235.0)
                        }
                        if (isSelected) {
                            hbox {
                                button {
                                    addClass(Styles.icon, Styles.heartIcon)
                                }
                                addClass(Styles.defaultSpacing)
                                button {
                                    addClass(Styles.icon, Styles.trashcanIcon)
                                    action {
                                        subjects.remove(selectedItem)
                                    }
                                }
                                alignment = Pos.CENTER
                            }
                        }
                    }

                }
            }
            form {
                alignment = CENTER_RIGHT
                fieldset {
                    field("Enter Subject Name:") {
                        textfield(input)
                    }
                }
                button("Add New Subject") {
                    action {
                        subjects.add(input.value)
                        input.value = ""
                    }
                }
            }
        }
        vbox {
            setPrefSize(15.0, 700.0)
        }
    }
    init {
        subjects.add("CS 346")
        subjects.add("CS 446")
        subjects.add("CS 348")
    }
}