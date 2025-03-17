package com.example.tallerinterview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    private val _loginState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState


    fun login(user: String, password: String) {

        viewModelScope.launch {
            _loginState.emit(true)
            delay(5_000)
            _loginState.emit(false)
        }

    }

}
