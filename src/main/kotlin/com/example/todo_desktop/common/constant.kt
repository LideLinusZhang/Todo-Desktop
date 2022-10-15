package com.example.todo_desktop.common

class constant {
    companion object {
        val DUE_DATE_TODAY = "Today"
        val DUE_DATE_TOMORROW = "Tomorrow"
        val DUE_DATE_PICK_DATE = "Pick a date"
        val DUE_DATE_TITLE = "Due Date"

        //database
        val DB_URL = "jdbc:mysql://localhost:3306/anywhere_todo"
        val DB_TYPE = "com.mysql.cj.jdbc.Driver"
        val DB_USER_NAME = "root"
        val DB_PASSWORD = "123456"
        val DB_POOL_SIZE = 10

        val DB_CONFIG = dbConfig(DB_URL, DB_TYPE, DB_USER_NAME, DB_PASSWORD)

    }
}

class dbConfig(val url: String, val type: String, val user: String, val password: String)