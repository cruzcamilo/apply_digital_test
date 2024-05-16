package com.applydigitaltest.domain.model

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

data class Article(
    val title: String,
    val author: String,
    val createdAt: String,
    val url: String,
) {
    companion object {
        fun getTimeSinceCreated(createdAt: ZonedDateTime): String {
            val ldt = LocalDateTime.now()
            val ldtZoned = ldt.atZone(ZoneId.systemDefault())
            val now = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"))

            val difference = Duration.between(now, createdAt)

            val days = Math.abs(difference.toDays())
            val hours = Math.abs(difference.toHours() % 24)
            val minutes = Math.abs(difference.toMinutes() % 60)

            return if (days == 1L) {
                "Yesterday"
            } else if(hours in 1..22) {
                if (minutes > 0) {
                    val minutesFromHour = ((minutes.toFloat()/60)*10).toInt()
                    "${hours}.${minutesFromHour}h"
                } else {
                    "${hours}h"
                }
            } else if (minutes < 59L) {
                "${minutes}m"
            } else {
                "$days days"
            }
        }
    }

}
