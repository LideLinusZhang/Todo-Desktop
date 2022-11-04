package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.controller.ListController
import com.example.todo_desktop.data.ToDoInfo
import com.example.todo_desktop.service.RunCommandService
import com.example.todo_desktop.common.constant
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.geometry.Pos.CENTER
import javafx.geometry.Pos.CENTER_RIGHT
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import tornadofx.*
import java.io.File
import java.io.IOException
import java.lang.ProcessBuilder
import java.util.concurrent.TimeUnit
import edu.uwaterloo.cs.todo.lib.TodoCategoryModel
import edu.uwaterloo.cs.todo.lib.TodoItemModel
import edu.uwaterloo.cs.todo.lib.deserializeCategoryList
import edu.uwaterloo.cs.todo.lib.serializeCategoryList
import edu.uwaterloo.cs.todo.lib.deserializeItemList
import edu.uwaterloo.cs.todo.lib.serializeItemList
import java.util.Locale.Category
import java.time.LocalDate
import kotlin.properties.Delegates

//import org.codehaus.groovy.runtime.ProcessGroovyMethods

// Class definition for the list of
class SubjectListView : View("Subject List") {
    val input = SimpleStringProperty()
    val runCommandSerivce : RunCommandService = RunCommandService()
    //val mConstant : constant = constant()

    private var favorites = mutableListOf<Boolean>().observable()
    private var subjects = mutableListOf<String>().observable()
    private var categories = mutableListOf<TodoCategoryModel>().observable()
    private var subjectIDs = mutableListOf<Int>().observable()
    //private var items = mutableListOf<TodoItemModel>().observable()

    var s2: String = "abcdefg"
    val mToDoListView : ToDoListView by inject()


    override val root = hbox {
        // Execute command for listing out all current categories.
        var s1: String = runCommandSerivce.runCommand("./todo-cli-jvm list-categories --json", File("./bin"))
        // Debugging print message
        println(s1)
        // Translate json string into a list of Category objects
        categories = deserializeCategoryList(s1).toObservable()

        // Enroll the CategoryModel objects into the container
        for (i in categories) {
            if (i.favoured) {
                favorites.add(true)
            } else {
                favorites.add(false)
            }
            subjects.add(i.name)
            //subjectIDs.add(i.cate)
        }

        vbox {
            setPrefSize(15.0, 300.0)
        }

        vbox {
            // Header for the list of subjects
            vbox {
                alignment = CENTER
                setPrefSize(160.0,40.0)
                label("SUBJECTS") {
                    style = "-fx-font: 20 arial;"
                    minWidth(50.0)
                }
            }
            // The actual list of subjects
            listview(subjects) {
                setPrefSize(160.0, 475.0)
                onDoubleClick {
                    println("double click on subject list")
                    val doubleClickIdx = selectionModel.selectedIndices[0]+1

                    // Set current Category to the selected one.
                    constant.curCategory = doubleClickIdx
                    // First check if the user is clicking the current subject:

                    // Not clicking current branch -->
                    // Call CLI & search for tasks (with selectedItem as parameter)
                    var loadNewItemListCmd: String = "./todo-cli-jvm list-items " + doubleClickIdx.toString() + " --json"
                    println(loadNewItemListCmd)
                    var newItems: String = runCommandSerivce.runCommand(loadNewItemListCmd, File("./bin"))
                    // Delete all tasks in current list.
                    val tmp : MutableList<TodoItemModel> = deserializeItemList(newItems).toObservable()
                    mToDoListView.records.removeAll(mToDoListView.records)

                    // Refill content from database's result
                    for (i in tmp) {
                        //record.add(ToDoInfo(text.value, listController.currPriority, listController.currDueDate))
                        /*mToDoListView.records.add(ToDoInfo(i.name, i.importance.ordinal,
                            i.deadline?.let { LocalDate(it.year, i.deadline.monthNumber, i.deadline.dayOfMonth) })

                         */
                        val mYear: Number? = i.deadline?.year
                        val mMonth: Number? = i.deadline?.monthNumber
                        val mDay: Number? = i.deadline?.dayOfMonth
                        if (mYear == null || mMonth == null || mDay == null) {
                            mToDoListView.records.add(ToDoInfo(i.name, i.importance.ordinal, null))
                        } else {
                            val mYearInt = mYear.toInt()
                            val mMonthInt = mMonth.toInt()
                            val mDayInt = mDay.toInt()
                            mToDoListView.records.add(ToDoInfo(i.name, i.importance.ordinal, LocalDate.of(mYearInt, mMonthInt, mDayInt)))
                        }

                    }
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
                                alignment = CENTER
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