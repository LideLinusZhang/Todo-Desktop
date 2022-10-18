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

        // priority
        val PRIORITY_CRITICAL = "Critical"
        val PRIORITY_HIGH = "High"
        val PRIORITY_MEDIUM = "Medium"
        val PRIORITY_LOW = "Low"
        val PRIORITY_VERY_LOW = "Very Low"

        // sort option
        val SORT_BY_DEFAULT = "Default"
        val SORT_BY_PRIORITY = "Priority"
        val SORT_BY_DUE_DATE = "Due Date"

        // sort option index
        val SORT_DEFAULT_INDEX = 0
        val SORT_PRIORITY_INDEX = 1
        val SORT_DUE_INDEX = 2

    }
}

class dbConfig(val url: String, val type: String, val user: String, val password: String)