package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import javafx.scene.Parent
import tornadofx.*
import javafx.scene.layout.HBox

class HeaderView : View ("Header View") {
    val osName = System.getProperty("os.name")


    override val root = vbox {
        vbox {
            if (osName.startsWith("Mac")) {
                vbox {
                    setPrefSize(700.0, 20.0)
                }
            }
        }
        menubar() {
            if (osName.startsWith("Mac")) {
                useSystemMenuBarProperty().set(true)
            }
            menu("Appearence") {
                item("Font")
                item("Banner Background")
                item("Text Color")
            }
            menu("Help") {
                item("Help Page")
                item("Report an Issue")
            }
        }
    }
}