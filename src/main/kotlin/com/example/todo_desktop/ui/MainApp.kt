package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.common.constant
import com.example.todo_desktop.controller.databaseController
import tornadofx.*


class MainApp: App(MainView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun main() {
    val mDbController = databaseController(constant.DB_CONFIG)
    mDbController.initDb()
//
//    transaction {
//        SchemaUtils.create(ToDos)
//
//        ToDo.new {
//            author = "andr11111ew"
//            content = "abc"
//            dueDate = "2022-10-20"
//            isCompleted = false
//            priority = 10
//        }
//
//    }

    launch<MainApp>()
}
