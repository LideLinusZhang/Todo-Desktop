package com.example.todo_desktop.service

import java.io.BufferedReader
import java.io.InputStreamReader

class RunCommandService {
    private val rt : Runtime = Runtime.getRuntime();

    fun runCommand(commands : Array<String>) : String {
        val proc = rt.exec(commands)
        val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
        return stdInput.readText()
    }
}