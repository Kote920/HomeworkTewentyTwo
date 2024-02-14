package com.example.homeworktewentytwo.presentation.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDateString(): String {
    val dateFormat = SimpleDateFormat("d MMMM 'at' h:mm a", Locale.getDefault())
    return dateFormat.format(Date(this * 1000))
}
