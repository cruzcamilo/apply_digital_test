package com.applydigitaltest.domain

import com.applydigitaltest.domain.model.Article
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZonedDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArticleTest {
    val article = Article(
        "The iPad Pro Manifesto (2024 Edition)",
        "kjkjadksj",
        ZonedDateTime.now().minusDays(1).toString()
    )

    @Test
    fun `getHowLongAgoWasCreated when createdAt was yesterday`() {
        val result = article.getTimeSinceCreated()

        assertEquals("Yesterday", result)
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was hours ago`() {
        val article = article.copy(
            createdAt = ZonedDateTime.now().minusHours(2).toString()
        )

        val result = article.getTimeSinceCreated()

        assertEquals("2h", result, )
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was minutes ago`() {
        val article = article.copy(
            createdAt = ZonedDateTime.now().minusMinutes(30).toString()
        )

        val result = article.getTimeSinceCreated()

        assertEquals( "30m", result)
    }

    @Test
    fun `getHowLongAgoWasCreated when createdAt was 90 minutes ago`() {
        val article = article.copy(
            createdAt = ZonedDateTime.now().minusHours(1).minusMinutes(30).toString()
        )

        val result = article.getTimeSinceCreated()

        assertEquals("1.5h", result, )
    }
}