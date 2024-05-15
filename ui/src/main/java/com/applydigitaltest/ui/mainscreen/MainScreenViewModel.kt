package com.applydigitaltest.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
): ViewModel() {

    private val _mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val mainScreenUiState : StateFlow<MainScreenUiState> get() = _mainScreenUiState

    init {
        getArticles()
    }
    fun getArticles() {
        viewModelScope.launch {
            val articlesList = getArticlesUseCase.invoke()
            _mainScreenUiState.value = MainScreenUiState.Success(articlesList)
        }
    }
}

sealed interface MainScreenUiState {
    data object Loading: MainScreenUiState
    data class Success(val articles: List<Article>): MainScreenUiState
}