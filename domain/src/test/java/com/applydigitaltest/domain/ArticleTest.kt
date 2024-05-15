package com.applydigitaltest.domain

import com.applydigitaltest.domain.model.Article
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId

class ArticleTest {
    private val ldt = LocalDateTime.now()
    private val ldtZoned = ldt.atZone(ZoneId.systemDefault())
    private val now = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"))

    @Test
    fun `getHowLongAgoWasCreated when createdAt was yesterday`() {
        val result = Article.getTimeSinceCreated(now.minusDays(1))

        assertEquals("Yesterday", result)
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was hours ago`() {
        val result = Article.getTimeSinceCreated(now.minusHours(2))

        assertEquals("2h", result, )
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was minutes ago`() {
        val result = Article.getTimeSinceCreated(now.minusMinutes(30))

        assertEquals( "30m", result)
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was 90 minutes ago`() {
        val result = Article.getTimeSinceCreated(now.minusHours(1).minusMinutes(30))

        assertEquals("1.5h", result)
    }
}