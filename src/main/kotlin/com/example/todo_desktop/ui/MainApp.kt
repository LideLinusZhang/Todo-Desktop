package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import tornadofx.*


class MainApp: App(MainView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun main() {

    launch<MainApp>()
}
