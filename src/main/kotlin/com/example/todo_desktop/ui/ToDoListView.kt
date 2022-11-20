package com.example.todo_desktop.ui

import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import tornadofx.*
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.app.Styles.Companion.defaultSpacing
import com.example.todo_desktop.app.Styles.Companion.mediumText
import com.example.todo_desktop.app.Styles.Companion.smallSpacing
import com.example.todo_desktop.app.Styles.Companion.smallText
import com.example.todo_desktop.common.constant
import com.example.todo_desktop.controller.ListController
import com.example.todo_desktop.data.ToDoInfo
import com.example.todo_desktop.service.RunCommandService
import com.example.todo_desktop.ui.ToDoListView
import edu.uwaterloo.cs.todo.lib.TodoCategoryModel
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import java.awt.TextField
import java.time.LocalDate
import edu.uwaterloo.cs.todo.lib.TodoItemModel
import edu.uwaterloo.cs.todo.lib.deserializeCategoryList
import edu.uwaterloo.cs.todo.lib.deserializeItemList
import java.io.File
import java.util.*
import javax.swing.text.Style

class ToDoListView : View("ToDo Content") {


    var items = mutableListOf<TodoItemModel>().observable()
    // This might need to be global
    companion object {
        val records = mutableListOf<ToDoInfo>().observable()
    }

    private val DueDateList = FXCollections.observableArrayList("Today", "Tomorrow", "Pick a date")
    private val PriorityList =
        FXCollections.observableArrayList(
            constant.PRIORITY_CRITICAL,
            constant.PRIORITY_HIGH,
            constant.PRIORITY_MEDIUM,
            constant.PRIORITY_LOW,
            constant.PRIORITY_VERY_LOW)
    private val SortbyList =
        FXCollections.observableArrayList(
            constant.SORT_BY_DEFAULT,
            constant.SORT_BY_PRIORITY,
            constant.SORT_BY_DUE_DATE,
            constant.SORT_BY_STAR)

    private var selectedDue = SimpleStringProperty()
    private var selectedPri = SimpleStringProperty()
    private val selectedSort = SimpleStringProperty()
    private val dateProperty = SimpleObjectProperty<LocalDate>()

    val input = SimpleStringProperty()

    val listController : ListController by inject()
    val runCommandSerivce : RunCommandService = RunCommandService()
    val subjectListView : SubjectListView = SubjectListView()

    // constant
    private val PICK_DATE_INDEX = 2
    private val DUE_DATE_DEFAULT = 0

    override val root = vbox {
        println("ToDoListView: outermost vbox")
        stylesheets.add("org/kordamp/bootstrapfx/bootstrapfx.css")
        val subListStr: String = runCommandSerivce.runCommand("./todo-cli-jvm list-categories --json", File("./bin"))
        var tmpSubjects = mutableListOf<TodoCategoryModel>().observable()
        if (subListStr.substring(0,2) != "[]") {
            tmpSubjects = deserializeCategoryList(subListStr).toObservable()
            println("./todo-cli-jvm list-items " + tmpSubjects[0].uniqueId.toString() + " --uuid")
            println(tmpSubjects[0].uniqueId.toString())
            items = deserializeItemList(runCommandSerivce.runCommand(
                "./todo-cli-jvm list-items " + tmpSubjects[0].uniqueId.toString() + " --json --uuid", File("./bin"))).toObservable()
        }
        hbox().apply{
            println("ToDoListView: hbox containing padding")
            val currInfo : ToDoInfo

            paddingLeft = 10.0
            paddingRight = 10.0
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

            // hbox store the sort by button
            hbox {
                addClass(defaultSpacing)
                alignment = Pos.BOTTOM_RIGHT
                hboxConstraints { hGrow = Priority.ALWAYS }
                label ("Sort by") { addClass(Styles.mediumText) }
                combobox(selectedSort, SortbyList ){
                    addClass(smallText)
                    selectionModel.select(constant.SORT_BY_DEFAULT)

                    selectedSort.onChange {
                        listController.sortOption = it.toString()
                        listController.triggerSortOption(records)
                    }

                }
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
                        label(it.content)

                        // Display prioirty and due date
                        hbox{
                            addClass(smallSpacing)
                            label("Priority: ${listController.IntToPrioiry(it.priority)}")

                            // calendar icon
                            label {
                                addClass(Styles.smallIcon, Styles.calendarIcon)
                            }
                            label(listController.DateToString(it.date)) {
                                val style = when (it.date?.isBefore(LocalDate.now())) {
                                    true -> Styles.redText
                                    else -> Styles.blueText
                                }
                                addClass(style)
                            }
                        }
                    }

                    if (isSelected) {
                        val rmIdx = selectionModel.selectedIndices[0]
                        println("line 147")
                        // contain favorite and delete button
                        hbox {
                            button {
                                var heartStyle = listController.getStarStyle(it)
                                addClass(Styles.icon, heartStyle)
                                action {
                                    heartStyle = listController.changeStarStatus(it)
                                    addClass(Styles.icon, heartStyle)
                                    selectionModel.clearSelection()
                                    selectionModel.select(it)
                                    listController.triggerSortOption(records)
                                }
                            }

                            addClass(defaultSpacing)

                            button {
                                addClass(Styles.icon, Styles.trashcanIcon)
                                action {
                                    var delCmd: String = "./todo-cli-jvm delete-item " + records[rmIdx].uniqueID + " --uuid"
                                    println(delCmd)
                                    runCommandSerivce.runCommand(delCmd, File("./bin"))
                                    deleteTodo(records, selectedItem)
                                }
                            }
                            alignment = Pos.CENTER
                        }
                    }
                }
            }
            // when delete key is hit, delete the current Todo
            setOnKeyPressed {
                if (it.code.equals(KeyCode.BACK_SPACE)) {
                    deleteTodo(records, selectedItem)
                } else if (it.code.equals(KeyCode.W)) {
                    println("W key pressed on item list")
                    val selectedIdx = selectionModel.selectedIndices[0]
                    if (selectedIdx != 0) {
                        val tmpString = records[selectedIdx - 1]
                        records.removeAt(selectedIdx - 1)
                        records.add(selectedIdx, tmpString)
                        println("Item switched up")
                    }
                } else if (it.code.equals(KeyCode.S)) {
                    println("S key pressed on item list")
                    val selectedIdx = selectionModel.selectedIndices[0]
                    if (selectedIdx != records.size-1) {
                        val tmpString = records[selectedIdx + 1]
                        records.add(selectedIdx, tmpString)
                        records.removeAt(selectedIdx + 2)
                        println("Item switched down")
                    }
                }
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

                hbox {
                    // hbox to add properties for a Todo
                    hbox {

                        vbox {
                            label("Due Date")

                            // Due date
                            combobox(selectedDue, DueDateList) {
                                // set default due date -> today
                                selectionModel.select(constant.DUE_DATE_TODAY)

                                selectedDue.onChange {
                                    var mDate = selectedDue.value
                                    if(it.equals(DueDateList[PICK_DATE_INDEX])) {
                                        datepicker(dateProperty) {
                                            show()
                                            setOnAction {
                                                mDate = value.toString()
                                                setPickDate(mDate, DueDateList)
                                                setDueDate(mDate)
                                            }
                                        }
                                    } else {
                                        DueDateList[PICK_DATE_INDEX] = constant.DUE_DATE_PICK_DATE
                                    }
                                    if (mDate != null)
                                        setDueDate(mDate)
                                }
                            }
                        }

                        vbox {
                            label("Priority")

                            // Priority
                            combobox(selectedPri, PriorityList) {
                                // set default priority -> medium
                                selectionModel.select(constant.PRIORITY_MEDIUM)

                                selectedPri.onChange {
                                    setPriority(selectedPri.value)
                                }
                            }
                        }
                    }

                    // Hbox to store the add button
                    hbox{
                        alignment = Pos.CENTER_RIGHT
                        hboxConstraints { hGrow = Priority.ALWAYS }

                        button("Add New Todo") {
                            action {
                                addToDo(records, input)
                            }
                            styleClass.setAll("btn","btn-danger")
                        }
                    }
                }
            }
        }
    }

    override fun onDock() {
        root.requestFocus()
    }

    private fun addToDo(record : MutableList<ToDoInfo>, text : SimpleStringProperty) {
        println("TDLV: 305")
        if (text.value == "") return
        var tmpCmd: String = "./todo-cli-jvm add-item --search-category-by id " + constant.curCategory +  " "
        tmpCmd = tmpCmd + text.value + " --uuid"
        println(tmpCmd)
        println("TDLV: 310")
        runCommandSerivce.runCommand(tmpCmd, File("./bin"))

        // Query the database to obtain the last (most recent) item of current category.
        println("line 314")
        var tmpitems = mutableListOf<TodoItemModel>().observable()
        tmpitems = deserializeItemList(runCommandSerivce.runCommand(
            "./todo-cli-jvm list-items " + constant.curCategory + " --json --uuid", File("./bin"))).toObservable()
        println("line 318")
        record.add(ToDoInfo(text.value, listController.currPriority, listController.currDueDate, false, tmpitems[tmpitems.size-1].uniqueId))
        text.value = ""
        listController.addToDo(records)
        println("line 322")
        //runCommandSerivce.runCommand(tmpCmd, File("./bin"))
    }

    private fun deleteTodo(record: MutableList<ToDoInfo>, selectedItem : ToDoInfo?) {
        record.remove(selectedItem)
        listController.deleteToDo(selectedItem, records)
    }

    private fun setPickDate(text : String, list : ObservableList<String>) {
        list[PICK_DATE_INDEX] = text
    }

    private fun setPriority(prio : String) {
        listController.setPriority(prio)
    }

    private fun setDueDate(date : String) {
        listController.convertDate(date)
    }

}
