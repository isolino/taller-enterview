package com.example.tallerinterview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    private val _loginState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    private val _events: Channel<LoginEvents> = Channel()
    val events = _events.receiveAsFlow()


    fun login(user: String, password: String) {

        val showError = listOf(true, false).random()

        viewModelScope.launch {
            _loginState.emit(true)
            delay(5_000)
            _loginState.emit(false)

            if (showError) {
                _events.send(LoginEvents.Error("ERROR: Wrong Password"))
            } else {
                _events.send(LoginEvents.Success("Success Login for $user"))
            }
        }

    }

    sealed class  LoginEvents{
        data class Success( val message: String): LoginEvents()
        data class Error( val message: String): LoginEvents()
    }

}
