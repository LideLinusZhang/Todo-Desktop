package com.example.todo_desktop.controller

import com.example.todo_desktop.common.constant
import com.example.todo_desktop.common.dbConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class databaseController(val config_info: dbConfig) {

    val config = HikariConfig().apply {
        jdbcUrl = config_info.url
        driverClassName = config_info.type
        username = config_info.user
        password = config_info.password
        maximumPoolSize = constant.DB_POOL_SIZE
    }

    val dataSource = HikariDataSource(config)

    fun initDb() {
        Database.connect(dataSource)
    }

}