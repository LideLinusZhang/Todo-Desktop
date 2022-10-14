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

        //Default Texts
        val HeaderText by cssclass()
        val boldText by cssclass()


        //Default looks
        val defaultSpacing by cssclass()
        val smallSpacing by cssclass()


        // Colors
        val darkTextColor = c("#666")

        //Icons
        val icon by cssclass()
        val small by cssclass()
        val medium by cssclass()
        val large by cssclass()
        val completeIcon by cssclass()
        val trashcanIcon by cssclass()
        val heartIcon by cssclass()
        val sunnyDayIcon by cssclass()
    }

    init {
        // Default text
        boldText {
            fontWeight = BOLD
        }

        HeaderText {
            fontSize = 18.px
            textFill = BLACK
            fontWeight = BOLD
        }


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

        sunnyDayIcon {
            shape = "M31,50.5c0-1.104-0.896-2-2-2h-9c-1.104,0-2,0.896-2,2s0.896,2,2,2h9C30.104,52.5,31,51.604,31,50.5z M81,48.5h-8c-1.104,0-2,0.896-2,2s0.896,2,2,2h8c1.104,0,2-0.896,2-2S82.104,48.5,81,48.5z M52.5,81.881v-9c0-1.104-0.896-2-2-2s-2,0.896-2,2v9c0,1.104,0.896,2,2,2S52.5,82.985,52.5,81.881z M48.5,19.881v9c0,1.104,0.896,2,2,2s2-0.896,2-2v-9c0-1.104-0.896-2-2-2S48.5,18.776,48.5,19.881z M37.092,67.799c0.781-0.781,0.781-2.047,0-2.828s-2.047-0.781-2.828,0l-5.844,5.844c-0.781,0.781-0.781,2.047,0,2.828 c0.391,0.391,0.902,0.586,1.414,0.586s1.023-0.195,1.414-0.586L37.092,67.799z M74.752,30.139c0.781-0.781,0.781-2.047,0-2.828s-2.047-0.781-2.828,0l-5.844,5.844c-0.781,0.781-0.781,2.047,0,2.828 c0.391,0.391,0.902,0.586,1.414,0.586s1.023-0.195,1.414-0.586L74.752,30.139z M68.26,64.971c-0.781-0.781-2.047-0.781-2.828,0c-0.781,0.78-0.781,2.047,0,2.828l5.842,5.844 c0.391,0.391,0.902,0.586,1.414,0.586s1.023-0.195,1.414-0.586c0.781-0.78,0.781-2.047,0-2.828L68.26,64.971z M36.443,33.154L30.6,27.311c-0.781-0.781-2.047-0.781-2.828,0s-0.781,2.047,0,2.828l5.844,5.844 c0.391,0.391,0.902,0.586,1.414,0.586s1.023-0.195,1.414-0.586C37.225,35.201,37.225,33.936,36.443,33.154z M51.299,33.184c-9.478,0-17.188,7.711-17.188,17.189s7.711,17.189,17.188,17.189c9.479,0,17.189-7.711,17.189-17.189S60.777,33.184,51.299,33.184z M51.299,63.562c-7.272,0-13.188-5.917-13.188-13.189s5.916-13.189,13.188-13.189 s13.189,5.917,13.189,13.189S58.571,63.562,51.299,63.562z"
        }

    }
}