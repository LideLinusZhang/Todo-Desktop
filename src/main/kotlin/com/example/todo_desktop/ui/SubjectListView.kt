package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

// Class definition for the list of
class SubjectListView : View("Subject List") {
    private var records = mutableListOf<String>().observable()
    val input = SimpleStringProperty()
    override val root = vbox {
        listview(records) {
            cellFormat {
                text = it
            }
        }
        form {
            fieldset {
                field("Enter Subject Name") {
                    textfield(input)
                }
            }
            button("Add New Todo") {
                action {
                    records.add(input.value)
                    input.value = ""
                }
            }
        }

    }
    init {
        records.add("CS 346")
        records.add("CS 446")
        records.add("CS 348")
    }
}