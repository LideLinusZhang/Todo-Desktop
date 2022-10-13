package com.example.todo_desktop.ui

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*


class listView : View("ToDo Content") {

    private val records = mutableListOf<String>().observable()

    val taskName = SimpleStringProperty()
    val taskPrio = SimpleIntegerProperty()
    val taskDue = SimpleStringProperty()

    override val root = vbox {
        listview (records) {
            cellFormat {
                text = it
            }
        }

        form {
            fieldset {
                field("Enter your Todo") {
                    textfield(taskName)
                    textfield(taskPrio)
                    textfield(taskDue)
                }

                button("Add New Todo") {
                    action {
                        records.add(taskName.value)
                        taskName.value = ""
                    }
                }
            }
        }



    }

}
