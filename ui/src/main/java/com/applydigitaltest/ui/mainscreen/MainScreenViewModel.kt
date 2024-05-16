package com.applydigitaltest.ui.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.usecase.FetchAndSaveUseCase
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val fetchAndSaveUseCase: FetchAndSaveUseCase
): ViewModel() {
    private val articleList = mutableListOf<Article>()

    private val _mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val mainScreenUiState : StateFlow<MainScreenUiState> get() = _mainScreenUiState

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            _mainScreenUiState.value = MainScreenUiState.Loading
            fetchAndSaveUseCase.invoke()
            getArticlesUseCase.invoke().collect {
                articleList.addAll(it)
                _mainScreenUiState.value = MainScreenUiState.Success(articleList)
                Log.d(MainScreenViewModel::class.java.name, "Articles size ${it.size}")
            }
        }
    }

    fun removeArticle(currentItem: Article) {
        _mainScreenUiState.update {
            val mutableList = (it as MainScreenUiState.Success).articles.toMutableList()
            mutableList.remove(currentItem)
            MainScreenUiState.Success(mutableList)
        }
    }
}

sealed interface MainScreenUiState {
    data object Loading: MainScreenUiState
    data class Success(val articles: List<Article>): MainScreenUiState
}