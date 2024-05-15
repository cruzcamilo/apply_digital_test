package com.applydigitaltest.ui.detailscreen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailScreenRoute(url: String) {
    DetailScreen(url)
}

@Composable
fun DetailScreen(url: String) {
    if (url.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "This article does not have an url")
        }
    } else {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                WebView(it).apply {
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    DetailScreen("")
}
