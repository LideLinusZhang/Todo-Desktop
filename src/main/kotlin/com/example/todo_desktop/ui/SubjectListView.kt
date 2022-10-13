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
            fieldset("New Subject") {
                field("Enter Subject Name") {
                    textfield(input)
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