package com.example.api_mvvm_kotlin.viewmodel

import Fact
import FactsApi
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed interface FactUIState {
    data class Success(val fact: List<Fact>): FactUIState
    object Error: FactUIState
    object Loading: FactUIState
}

class FactViewModel: ViewModel() {
    var factUIState: FactUIState by mutableStateOf<FactUIState>(FactUIState.Loading)
        private set

    init {
        getFactsList()
    }

    private fun getFactsList() {
        viewModelScope.launch {
            var factsApi: FactsApi? = null
            try {
                factsApi = FactsApi!!.getInstance()
                factUIState = FactUIState.Success(factsApi.getFacts())
            } catch (e: Exception) {
                Log.d("FACTVIEWMODEL", e.message.toString())
                factUIState = FactUIState.Error
            }
        }
    }
}