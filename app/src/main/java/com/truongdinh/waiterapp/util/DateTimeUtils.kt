package com.truongdinh.waiterapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
private val dateTimeFormatter =
    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toDisplayDateTime(): String {
    return format(dateTimeFormatter)
}