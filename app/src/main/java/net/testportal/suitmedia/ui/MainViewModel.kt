package net.testportal.suitmedia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.testportal.suitmedia.data.remote.ReqresinApiService
import net.testportal.suitmedia.ui.third.User

class MainViewModel(
    private val service: ReqresinApiService,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            val response = service.fetchUsers()
            Log.d("blah", "$response")
        }
    }

    fun fetchUsers(page: Int, perPage: Int = 10) {
        // _state.update { it.copy(isLoading = true) }
        // viewModelScope.launch {
        //     service
        // }
    }

    fun getString(id: Int): String = when (id) {
        1 -> "First Screen"
        2 -> "Second Screen"
        3 -> "Third Screen"
        else -> "Palindrome Dialog"
    }

    data class State(
        val users: List<User> = emptyList(),
        val isLoading: Boolean = false,
    ) {
        val isEmpty: Boolean = users.isEmpty()
    }
}
