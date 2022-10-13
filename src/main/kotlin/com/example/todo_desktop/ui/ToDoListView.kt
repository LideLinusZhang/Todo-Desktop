package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import tornadofx.*
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.app.Styles.Companion.defaultSpacing
import com.example.todo_desktop.app.Styles.Companion.smallSpacing
import com.example.todo_desktop.controller.ListController
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority

class ToDoListView : View("ToDo Content") {

    private val records = mutableListOf<String>().observable()

    val input = SimpleStringProperty()

    val listController : ListController by inject()

    override val root = vbox {
        stylesheets.add("org/kordamp/bootstrapfx/bootstrapfx.css")
        addClass(Styles.issueList)
        listview (records) {
            cellFormat {
                graphic = HBox().apply {
                    addClass(defaultSpacing)
                    hbox {
                        button {
                            addClass(Styles.icon, Styles.completeIcon)
                            action {
                                print("adawd")
                            }
                        }
                        alignment = Pos.CENTER
                    }
                    vbox {
                        addClass(smallSpacing)
                        hboxConstraints { hGrow = Priority.ALWAYS }
                        label(it)
                        label("Tasks")
                    }

                    if (isSelected) {
                        // contain favorite and delete button
                        hbox {
                            button {
                                addClass(Styles.icon, Styles.heartIcon)
                            }

                            addClass(defaultSpacing)

                            button {
                                addClass(Styles.icon, Styles.trashcanIcon)

                                action {
                                    print(selectedItem)
                                    deleteTodo(records, selectedItem)
                                }
                            }
                            alignment = Pos.CENTER
                        }
                    }

                }
            }
        }

        form {
            fieldset {
                field("Enter your Todo") {
                    textfield(input) {
                        setOnKeyPressed {
                            if (it.code.equals(KeyCode.ENTER)) {
                                addToDo(records, input)
                            }
                        }
                    }
                }

                button("Add New Todo") {
                    action {
                        addToDo(records, input)
                    }
                    styleClass.setAll("btn","btn-danger")
                }
            }
        }

    }

    override fun onDock() {
        root.requestFocus()
    }

    private fun addToDo(record : MutableList<String>, text : SimpleStringProperty) {
        if (text.value == "") return

        record.add(text.value)
        text.value = ""
    }

    private fun deleteTodo(record: MutableList<String>, selectedItem : String?) {
        record.remove(selectedItem)
        listController.deleteToDo(selectedItem)
    }

}
