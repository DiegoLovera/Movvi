package com.diegolovera.movvi.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("MMM, yyyy", Locale.getDefault())
        return format.format(date)
    }
}