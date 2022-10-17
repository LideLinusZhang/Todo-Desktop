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
    private var favorites = mutableListOf<Boolean>().observable()
    val input = SimpleStringProperty()

    val mToDoListView : ToDoListView by inject()

    override val root = hbox {
        // Space holder
        vbox {
            setPrefSize(10.0, 700.0)
        }
        vbox {
            // Header for the list of subjects
            vbox {
                alignment = CENTER
                label("SUBJECTS") {
                    style = "-fx-font: 20 arial;"
                    minWidth(50.0)
                    minHeight(50.0)
                }
            }
            // The actual list of subjects
            listview(subjects) {
                setPrefSize(160.0, 500.0)
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
                    // Selected + pressing W --> Move subject up by 1
                    if (it.code.equals(KeyCode.W)) {
                        println("W key pressed on subject list")
                        val selectedIdx = selectionModel.selectedIndices[0]
                        // If user is not trying to move up the first subject
                        if (selectedIdx != 0) {
                            val tmpString = subjects[selectedIdx - 1]
                            val tmpBool = favorites[selectedIdx - 1]
                            subjects.removeAt(selectedIdx - 1)
                            favorites.removeAt(selectedIdx - 1)
                            subjects.add(selectedIdx, tmpString)
                            favorites.add(selectedIdx, tmpBool)
                            println("Item switched up")
                        }
                    // Selected + pressing S --> Move subject down by 1
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
                        // Conditions for button clicks on the cell
                        if (isSelected) {
                            val selectedIdx = selectionModel.selectedIndices[0]
                            val tmpString = subjects[selectedIdx]
                            hbox {
                                button {
                                    // If the selected cell is favorited.
                                    if (favorites[selectedIdx]) {
                                        addClass(Styles.icon, Styles.filledHeartIcon)
                                        action {
                                            subjects.removeAt(selectedIdx)
                                            subjects.add(selectedIdx, tmpString)
                                            favorites[selectedIdx] = false
                                            selectionModel.select(subjects[selectedIdx])
                                        }
                                    // If the selected cell is not favorited.
                                    } else {
                                        addClass  (Styles.icon, Styles.heartIcon)
                                        action {
                                            subjects.removeAt(selectedIdx)
                                            subjects.add(selectedIdx, tmpString)
                                            addClass (Styles.icon, Styles.filledHeartIcon)
                                            favorites[selectedIdx] = true
                                            selectionModel.select(subjects[selectedIdx])
                                        }
                                    }
                                }
                                addClass(Styles.defaultSpacing)
                                // Show delete button
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
            // Form for add a new subject to the list
            form {
                alignment = CENTER_RIGHT
                fieldset {
                    field("Enter Subject Name:") {
                        textfield(input)
                    }
                }
                // Button to insert a new subject (with default fav value)
                button("Add New Subject") {
                    action {
                        subjects.add(input.value)
                        favorites.add(false)
                        input.value = ""
                    }
                }
            }
        }
        vbox {
            setPrefSize(15.0, 700.0)
        }
    }
    // Some sample data
    init {
        subjects.add("CS 346")
        subjects.add("CS 446")
        subjects.add("CS 348")
        favorites.add(true)
        favorites.add(true)
        favorites.add(true)
    }
}