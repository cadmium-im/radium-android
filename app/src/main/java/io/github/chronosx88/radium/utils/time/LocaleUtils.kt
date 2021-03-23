/*
 * This is the source code of Telegram for Android v. 1.3.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package io.github.chronosx88.radium.utils.time

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import io.github.chronosx88.radium.R
import java.util.*

// NOTE kindly taken from https://github.com/DrKLO/Telegram
object LocaleUtils {
    private lateinit var formatterDay: FastDateFormat
    private lateinit var formatterWeek: FastDateFormat
    private lateinit var formatterDayMonth: FastDateFormat
    private lateinit var formatterYear: FastDateFormat

    fun initFormatters(context: Context) {
        val locale = Locale.getDefault();
        val lang = locale.language.toLowerCase()
        formatterDayMonth = createFormatter(
            locale,
            context.getString(R.string.formatterMonth),
            "dd MMM"
        )
        formatterYear = createFormatter(
            locale,
            context.getString(R.string.formatterYear),
            "dd.MM.yy"
        )
        formatterWeek = createFormatter(
            locale,
            context.getString(R.string.formatterWeek),
            "EEE"
        )
        formatterDay = createFormatter(
            if (lang.equals("ar") || lang.equals("ko")) locale else Locale.US,
            if (DateFormat.is24HourFormat(context)) context.getString(R.string.formatterDay24H) else context.getString(R.string.formatterDay12H),
            if (DateFormat.is24HourFormat(context)) "HH:mm" else "h:mm a"
        )
    }

    private fun createFormatter(
        locale: Locale,
        format: String,
        defaultFormat: String
    ): FastDateFormat {
        var formatter: FastDateFormat?
        try {
            formatter = FastDateFormat.getInstance(format, locale)
        } catch (e: java.lang.Exception) {
            formatter = FastDateFormat.getInstance(defaultFormat, locale)
        }
        return formatter!!
    }

    fun stringForMessageListDate(date: Long): String? {
        try {
//            date *= 1000
            val rightNow = Calendar.getInstance()
            val day: Int = rightNow.get(Calendar.DAY_OF_YEAR)
            rightNow.setTimeInMillis(date)
            val dateDay: Int = rightNow.get(Calendar.DAY_OF_YEAR)
            return if (Math.abs(System.currentTimeMillis() - date) >= 31536000000L) {
                formatterYear.format(Date(date))
            } else {
                val dayDiff = dateDay - day
                if (dayDiff == 0 || dayDiff == -1 && System.currentTimeMillis() - date < 60 * 60 * 8 * 1000) {
                    formatterDay.format(Date(date))
                } else if (dayDiff > -7 && dayDiff <= -1) {
                    formatterWeek.format(Date(date))
                } else {
                    formatterDayMonth.format(Date(date))
                }
            }
        } catch (e: Exception) {
            Log.e("LOC_ERR", e.toString())
        }
        return "LOC_ERR"
    }
}