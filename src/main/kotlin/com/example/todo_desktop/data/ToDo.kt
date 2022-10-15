package com.example.todo_desktop.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Table.Dual.varchar


object ToDos : IntIdTable() {
    val author = varchar("author", 20)
    val content = varchar("content", 20)
    val dueDate = varchar("dueDate", 10)
    val isCompleted = bool("isCompleted")
    val priority = integer("priority")
}

class ToDo(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ToDo>(ToDos)
    var author by ToDos.author
    var content by ToDos.content
    var dueDate by ToDos.dueDate
    var isCompleted by ToDos.isCompleted
    var priority by ToDos.priority
}