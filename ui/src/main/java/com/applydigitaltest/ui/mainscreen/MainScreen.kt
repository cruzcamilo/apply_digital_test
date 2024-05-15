package com.applydigitaltest.ui.mainscreen

import androidx.compose.foundation.clickable
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
    onClickArticle: (String) -> Unit,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val feedUiState by mainScreenViewModel.mainScreenUiState.collectAsStateWithLifecycle()
    MainScreen(
        feedUiState = feedUiState,
        onClickArticle = onClickArticle
    )
}

@Composable
fun MainScreen(
    feedUiState: MainScreenUiState,
    onClickArticle: (String) -> Unit,
) {
    when(feedUiState) {

        Loading -> {}

        is Success -> {
            LazyColumn {
                items(feedUiState.articles) {
                    ArticleItem(article = it, onClickArticle = onClickArticle)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    onClickArticle: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(top = 16.dp)
        .clickable { onClickArticle(article.url) }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = article.title)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                text = "${article.author} - ${article.createdAt}"
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
        title = "The iPad Pro Manifesto (2024 Edition)",
        author = "kjkjadksj",
        createdAt = "Yesterday",
        url = ""
    )
    val articleList = listOf(article, article, article)
    val feedUiState = Success(articleList)

    MainScreen(
        feedUiState = feedUiState,
        onClickArticle = {}
    )
}
