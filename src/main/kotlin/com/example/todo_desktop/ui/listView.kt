package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import tornadofx.*
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.app.NavigatorButtonViewCss
import com.example.todo_desktop.app.Styles.Companion.defaultSpacing
import com.example.todo_desktop.app.Styles.Companion.smallSpacing
import javafx.geometry.Pos
import javafx.scene.layout.Priority

import org.kordamp.bootstrapfx.BootstrapFX

class listView : View("ToDo Content") {

    private val records = mutableListOf<String>().observable()

    val input = SimpleStringProperty()

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

                    hbox {
                        button {
                            addClass(Styles.icon, Styles.heartIcon)
                        }

                        addClass(defaultSpacing)

                        button {
                            addClass(Styles.icon, Styles.trashcanIcon)
                        }
                        alignment = Pos.CENTER
                    }
                }
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
                    styleClass.setAll("btn","btn-danger")
                }
            }
        }



    }

}
