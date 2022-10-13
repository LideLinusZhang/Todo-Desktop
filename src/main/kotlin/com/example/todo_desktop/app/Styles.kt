package com.example.todo_desktop.app

import javafx.scene.paint.Color
import javafx.scene.paint.Color.*
import javafx.geometry.Pos.CENTER
import javafx.geometry.Pos.CENTER_LEFT
import javafx.scene.Cursor.HAND
import javafx.scene.paint.Color.*
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.text.FontWeight.BOLD
import tornadofx.*


class Styles : Stylesheet(){
    companion object {

        val issueList by cssclass()

        //Default looks
        val defaultSpacing by cssclass()
        val smallSpacing by cssclass()

        //Icons
        val icon by cssclass()
        val small by cssclass()
        val medium by cssclass()
        val large by cssclass()
        val completeIcon by cssclass()
        val trashcanIcon by cssclass()
        val heartIcon by cssclass()
    }

    init {
        defaultSpacing {
            spacing = 10.px
        }

        smallSpacing {
            spacing = 5.px
        }

        issueList contains icon {
            translateY = 3.px
        }

        // Icons
        icon {
            minWidth = 16.px
            maxWidth = 16.px
            minHeight = 16.px
            maxHeight = 16.px
            backgroundColor += c("black")
            and(small) {
                minWidth = 12.px
                maxWidth = 12.px
                minHeight = 12.px
                maxHeight = 12.px
            }
            and(medium) {
                minWidth = 28.px
                maxWidth = 28.px
                minHeight = 28.px
                maxHeight = 28.px
            }
            and(large) {
                minWidth = 48.px
                maxWidth = 48.px
                minHeight = 48.px
                maxHeight = 48.px
            }
        }

        completeIcon {
            shape = "M20,28.5c-4.7,0-8.5-3.8-8.5-8.5s3.8-8.5,8.5-8.5s8.5,3.8,8.5,8.5S24.7,28.5,20,28.5z M20,12.5 c-4.1,0-7.5,3.4-7.5,7.5s3.4,7.5,7.5,7.5s7.5-3.4,7.5-7.5S24.1,12.5,20,12.5z M19.2,22.5c-0.4,0-0.7-0.1-0.9-0.4l-1.1-1.1c-0.2-0.2-0.2-0.5,0-0.7s0.5-0.2,0.7,0l1.1,1.1c0.1,0.1,0.1,0.1,0.2,0.1c0,0,0,0,0,0c0.1,0,0.2,0,0.2-0.1l2.7-2.7c0.2-0.2,0.5-0.2,0.7,0c0.2,0.2,0.2,0.5,0,0.7l-2.7,2.7C19.9,22.3,19.6,22.5,19.2,22.5C19.2,22.5,19.2,22.5,19.2,22.5z"
        }

        trashcanIcon {
            shape = "M6.5 1.75a.25.25 0 01.25-.25h2.5a.25.25 0 01.25.25V3h-3V1.75zm4.5 0V3h2.25a.75.75 0 010 1.5H2.75a.75.75 0 010-1.5H5V1.75C5 .784 5.784 0 6.75 0h2.5C10.216 0 11 .784 11 1.75zM4.496 6.675a.75.75 0 10-1.492.15l.66 6.6A1.75 1.75 0 005.405 15h5.19c.9 0 1.652-.681 1.741-1.576l.66-6.6a.75.75 0 00-1.492-.149l-.66 6.6a.25.25 0 01-.249.225h-5.19a.25.25 0 01-.249-.225l-.66-6.6z"
        }

        heartIcon {
            shape = "M20,27.3c-0.1,0-0.2,0-0.3,0c-0.9-0.2-4.1-1.2-6.6-5.1c-1.2-1.8-1.1-4.2,0.3-5.8c0.9-1,2.2-1.6,3.5-1.6 c1.1,0,2.2,0.4,3.1,1.1c0.9-0.7,1.9-1.1,3.1-1.1c1.4,0,2.6,0.6,3.5,1.6c1.4,1.5,1.5,3.9,0.3,5.8c-2.5,3.9-5.7,4.9-6.6,5.1 C20.2,27.2,20.1,27.3,20,27.3z M16.9,15.7c-1.1,0-2.1,0.5-2.8,1.3c-1.1,1.2-1.2,3.1-0.2,4.6c2.3,3.6,5.2,4.5,6,4.7l0.1,0c0,0,0.1,0,0.1,0c0.8-0.2,3.7-1.1,6-4.7c0.9-1.5,0.9-3.4-0.2-4.6c-0.7-0.8-1.7-1.3-2.8-1.3c-1,0-1.9,0.4-2.6,1.1c0,0-0.1,0.1-0.1,0.1c-0.2,0.2-0.5,0.2-0.7,0c0,0-0.1-0.1-0.1-0.1C18.9,16.1,17.9,15.7,16.9,15.7z"
        }

    }
}