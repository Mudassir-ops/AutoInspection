package com.example.autoinspectionapp.domain

import android.util.Log
import javax.inject.Inject

class LogsHelper @Inject constructor() {

    private val STACK_TRACE_LEVELS_UP_LINE = 3
    private val STACK_TRACE_LEVELS_UP_CLASS = 3

    fun createCompleteLog(tag: String, message: String) {
        val maxLogSize = 1000
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            val end =
                if ((i + 1) * maxLogSize > message.length) message.length else (i + 1) * maxLogSize
            createLog(tag, message.substring(start, end))
        }
    }

    fun createLog(logDetails: String) {
        val stack = Thread.currentThread().stackTrace
        val lineNr = stack[STACK_TRACE_LEVELS_UP_LINE].lineNumber
        val className = stack[STACK_TRACE_LEVELS_UP_CLASS].fileName
        Log.d("Log Output", "$lineNr  $className $logDetails")
    }

    fun createLog(tag: String, logDetails: String) {
        val stack = Thread.currentThread().stackTrace
        val lineNr = stack[STACK_TRACE_LEVELS_UP_LINE].lineNumber
        val className = stack[STACK_TRACE_LEVELS_UP_CLASS].fileName
        if (tag.isEmpty()) {
            Log.d("Log Output", "$lineNr  $className $logDetails")
        } else {
            Log.d(tag, "$lineNr  $className $logDetails")
        }
    }
}
