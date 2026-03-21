package com.example.cloudassessmentexam.core.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun convertLongToDateString(timestamp: Long, pattern: String): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val formatter = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault())
    return formatter.format(instant.atZone(ZoneId.systemDefault()))
}