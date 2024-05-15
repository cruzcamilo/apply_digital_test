package com.applydigitaltest.domain.model

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class Article(
    val title: String = "",
    val author: String,
    val createdAt: String,
) {
    fun getTimeSinceCreated(): String {
        val now = LocalDateTime.now()
        val createdDate = ZonedDateTime.parse(createdAt)
        val difference = Duration.between(now, createdDate)

        val days = Math.abs(difference.toDays())
        val hours = Math.abs(difference.toHours() % 24)
        val minutes = Math.abs(difference.toMinutes() % 60)

        println("Days $days Hours $hours Minutes $minutes")
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
