package com.example.todo_desktop.data

import java.time.LocalDate

class ToDoInfo(val info: String = "", val prio: Int = 3, val date: LocalDate? = LocalDate.now(), val fav: Boolean = false ) {
    var author = ""
    var content = info
    var priority = prio
    var dueDate = date
    var isCompleted = false
    var isStared = fav
}