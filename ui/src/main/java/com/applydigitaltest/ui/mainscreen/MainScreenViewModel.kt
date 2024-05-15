package com.applydigitaltest.ui.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
): ViewModel() {

    init {
        getArticles()
    }
    fun getArticles() {
        viewModelScope.launch {
            val result = getArticlesUseCase.invoke()
            result.forEach {
                Log.d("MainScreenViewModel", "Title: ${it.title} Author: ${it.author} Date: ${it.createdAt}")
            }
        }
    }
}
