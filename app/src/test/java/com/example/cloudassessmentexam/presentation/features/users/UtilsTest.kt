package com.example.cloudassessmentexam.presentation.features.users

import com.example.cloudassessmentexam.core.util.convertLongToDateString
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class UtilsTest {

    @Test
    fun `convertLongToDateString returns correct formatted date`() {
        // Given
        val timestamp = 1672531200000L // 2023-01-01
        val pattern = "MMMM dd, yyyy"

        // When
        val result = convertLongToDateString(timestamp, pattern)

        // Then
        val expected = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault()))

        assertEquals(expected, result)
    }

    @Test
    fun `convertLongToDateString with different pattern`() {
        // Given
        val timestamp = 1672531200000L // 2023-01-01
        val pattern = "dd/MM/yyyy"

        // When
        val result = convertLongToDateString(timestamp, pattern)

        // Then
        val expected = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault()))

        assertEquals(expected, result)
    }
}