package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import tornadofx.*


class listView : View("ToDo Content") {

    private val records = mutableListOf<String>().observable()

    val input = SimpleStringProperty()

    override val root = vbox {
        listview (records) {
            cellFormat {
                text = it
            }
        }

        form {
            fieldset {
                field("Enter your Todo") {
                    textfield(input)
                }

                button("Add New Todo") {
                    action {
                        records.add(input.value)
                        input.value = ""
                    }
                }
            }
        }



    }

}
