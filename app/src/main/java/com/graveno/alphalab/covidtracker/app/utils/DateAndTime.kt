package com.graveno.alphalab.covidtracker.app.utils

import com.graveno.alphalab.covidtracker.app.Constants
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateAndTime {
    val TAG : String = "DateAndTime"
    val constant : Constants = Constants()

    fun strToDate(dateString: String) : Date {
        val dateFormat : DateFormat = SimpleDateFormat(
            constant.summaryDateFormat,
            Locale.getDefault()
        )
        return dateFormat.parse(dateString)!!
    }

    fun milliSecondToStrDate(milliSeconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("dd-MMM-yyyy, hh:mm:ss", Locale.getDefault())
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}