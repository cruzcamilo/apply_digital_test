package com.applydigitaltest.ui.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
        onClickArticle = onClickArticle,
        onRefresh = mainScreenViewModel::getArticles
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    feedUiState: MainScreenUiState,
    onClickArticle: (String) -> Unit,
    onRefresh: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = feedUiState is Loading,
        onRefresh = onRefresh
    )

    when(feedUiState) {
        Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }

        is Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn {
                    items(feedUiState.articles) {
                        ArticleItem(article = it, onClickArticle = onClickArticle)
                    }
                }

                PullRefreshIndicator(
                    refreshing = feedUiState is Loading,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = if (feedUiState is Loading) Color.Red else Color.Green
                )
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
        url = "https://www.highcaffeinecontent.com/blog/20240514-The-iPad-Pro-Manifesto-(2024-Edition)"
    )
    val articleList = listOf(article, article, article)
    val feedUiState = Success(articleList)

    MainScreen(
        feedUiState = feedUiState,
        onClickArticle = {},
        onRefresh = {}
    )
}
