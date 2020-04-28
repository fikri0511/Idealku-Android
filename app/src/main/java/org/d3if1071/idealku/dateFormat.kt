package org.d3if1071.idealku

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertWaktu(systemTime: Long): String {
    return SimpleDateFormat("EEEE, dd MMMM yyyy")
        .format(systemTime).toString()
}
