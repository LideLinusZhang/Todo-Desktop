package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import tornadofx.*
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.app.Styles.Companion.defaultSpacing
import com.example.todo_desktop.app.Styles.Companion.smallSpacing
import com.example.todo_desktop.common.constant
import com.example.todo_desktop.controller.ListController
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import java.awt.TextField
import java.time.LocalDate

class ToDoListView : View("ToDo Content") {

    public val records = mutableListOf<String>().observable()

    private val DueDateList = FXCollections.observableArrayList("Due Date", "Today", "Tomorrow", "Pick a date")
    private var selectedDue = SimpleStringProperty()
    private val dateProperty = SimpleObjectProperty<LocalDate>()

    val input = SimpleStringProperty()

    val listController : ListController by inject()


    // constant
    private val PICK_DATE_INDEX = 3
    private val DUE_DATE_DEFAULT = 0

    override val root = vbox {
        stylesheets.add("org/kordamp/bootstrapfx/bootstrapfx.css")

        hbox().apply{
            paddingLeft = 10.0
            vbox {
                addClass(smallSpacing)
                hbox {
                    label {
                        addClass(Styles.icon, Styles.sunnyDayIcon)
                    }

                    addClass(smallSpacing)

                    label("Today") {
                        addClass(Styles.HeaderText)
                    }
                }

                label (listController.TodayInfo())
            }
        }

        addClass(defaultSpacing)
        addClass(Styles.issueList)
        listview (records) {
            cellFormat {
                graphic = HBox().apply {
                    addClass(defaultSpacing)
                    hbox {
                        button {
                            addClass(Styles.icon, Styles.completeIcon)
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
            // when delete key is hit, delete the current ToDo
            setOnKeyPressed {
                    if (it.code.equals(KeyCode.BACK_SPACE))
                        deleteTodo(records,selectedItem)
            }

        }

        form {
            fieldset {
                field("Enter your Todo") {
                    textfield(input) {
                        id = "myTextField"
                        text = "Add a task"

                        setOnMouseClicked { text = "" }

                        setOnKeyPressed {
                            if (it.code.equals(KeyCode.ENTER))
                                addToDo(records, input)
                        }
                    }
                }

                button("Add New Todo") {
                    action {
                        addToDo(records, input)
                    }
                    styleClass.setAll("btn","btn-danger")
                }

                combobox(selectedDue, DueDateList) {
                    selectionModel.select(DueDateList[DUE_DATE_DEFAULT])
                    onLeftClick{
                        selectionModel.select(DueDateList[DUE_DATE_DEFAULT])
                        setPickDate(constant.DUE_DATE_PICK_DATE, DueDateList)
                    }

                    selectedDue.onChange {
                        var mDate = selectedDue.value
                        if(it.equals(DueDateList[PICK_DATE_INDEX])) {
                            datepicker(dateProperty) {
                                show()
                                setOnAction {
                                    mDate = value.toString()
                                    setPickDate(mDate, DueDateList)
                                    listController.convertDate(mDate)
                                }
                            }
                        }
                        if (mDate != null)
                            listController.convertDate(mDate)
                    }
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

    private fun setPickDate(text : String, list : ObservableList<String>) {
        list[PICK_DATE_INDEX] = text
    }

}
