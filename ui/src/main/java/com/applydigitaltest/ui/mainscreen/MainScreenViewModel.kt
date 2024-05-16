package com.applydigitaltest.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.usecase.FetchAndSaveUseCase
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val fetchAndSaveUseCase: FetchAndSaveUseCase
): ViewModel() {

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
                _mainScreenUiState.value = MainScreenUiState.Success(it)
            }
        }
    }
}

sealed interface MainScreenUiState {
    data object Loading: MainScreenUiState
    data class Success(val articles: List<Article>): MainScreenUiState
}