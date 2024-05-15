package com.applydigitaltest.ui.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.ui.mainscreen.MainScreenUiState.Loading
import com.applydigitaltest.ui.mainscreen.MainScreenUiState.Success

@Composable
fun MainScreenRoute(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val feedUiState by mainScreenViewModel.mainScreenUiState.collectAsStateWithLifecycle()
    MainScreen(
        feedUiState = feedUiState
    )
}

@Composable
fun MainScreen(
    feedUiState: MainScreenUiState
) {
    when(feedUiState) {

        Loading -> {}

        is Success -> {
            LazyColumn {
                items(feedUiState.articles) {
                    ArticleItem(article = it)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    val createdAt = article.getTimeSinceCreated()

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = article.title)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                text = "${article.author} - $createdAt"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(color = Color.Black)
    }
}


@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    val article = Article(
        "The iPad Pro Manifesto (2024 Edition)",
        "kjkjadksj",
        "2024-05-14T19:14:09Z"
    )
    val articleList = listOf(article, article, article)
    val feedUiState = Success(articleList)

    MainScreen(
        feedUiState
    )
}

@Composable
@Preview(showBackground = true)
fun ArticlePreview() {
    val article = Article(
        "The iPad Pro Manifesto (2024 Edition)",
        "kjkjadksj",
        "2024-05-14T19:14:09Z"
    )
    Box(modifier = Modifier.padding(vertical = 8.dp)){
        ArticleItem(article)
    }
}
