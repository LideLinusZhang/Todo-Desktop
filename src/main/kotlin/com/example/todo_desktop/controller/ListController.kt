package com.example.todo_desktop.controller

import com.example.todo_desktop.common.constant
import tornadofx.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.time.format.DateTimeFormatter

class ListController : Controller() {

    var currDueDate : LocalDate = LocalDate.now()

    fun deleteToDo(text : String?) {

    }

    fun TodayInfo(): String {
        val sdf = SimpleDateFormat("EEEE, MMMM dd")
        return sdf.format(Date())
    }

    fun convertDate(date : String) {
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        currDueDate = when (date) {
            constant.DUE_DATE_PICK_DATE -> return
            constant.DUE_DATE_TITLE -> return
            constant.DUE_DATE_TODAY -> LocalDate.now()
            constant.DUE_DATE_TOMORROW -> LocalDate.now().plusDays(1)
            else -> LocalDate.parse(date, formatter)
        }
        println("currDueDate ${currDueDate.toString()}");
    }


}