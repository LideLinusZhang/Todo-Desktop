package com.example.todo_desktop.service

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit
import kotlin.reflect.KProperty

class RunCommandService {
    private val rt : Runtime = Runtime.getRuntime();

    // New implementation for runCommand using Process Building
    fun runCommand(str: String, workingDir: File): String {
        val parts = str.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(5, TimeUnit.SECONDS)
        return proc.inputStream.bufferedReader().readText()
    }
}