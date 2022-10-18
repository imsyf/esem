package net.testportal.suitmedia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.testportal.suitmedia.data.PalindromeChecker
import net.testportal.suitmedia.data.remote.ReqresinApiClient.PER_PAGE
import net.testportal.suitmedia.data.remote.ReqresinApiService
import net.testportal.suitmedia.data.remote.responses.UserDto
import net.testportal.suitmedia.ui.third.User

class MainViewModel(
    private val checker: PalindromeChecker,
    private val service: ReqresinApiService,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    fun setName(name: String): Unit = _state.update {
        it.copy(name = name)
    }

    fun setWord(word: String): Unit = _state.update {
        val isOrNot = if (checker.isPalindrome(word)) "is" else "is not"
        it.copy(word = word, output = "\"$word\" $isOrNot a palindrome.")
    }

    fun setSelected(selected: String): Unit = _state.update {
        it.copy(selected = selected)
    }

    fun fetch() {
        if (_state.value.isLastPage) return

        viewModelScope.launch {
            try {
                val response = service.fetchUsers(
                    page = _state.value.users.size / PER_PAGE + 1,
                )

                _state.update {
                    it.copy(
                        users = it.users + response.data.map(::toUser),
                        isLastPage = response.page == response.total_pages,
                        previousPage = response.page,
                        isRefreshing = false,
                    )
                }
            } catch (exception: Exception) {
                Log.d("blah", "${exception.message}")
            }
        }
    }

    fun resetPage(): Unit = _state.update {
        it.copy(
            users = emptyList(),
            previousPage = 0,
            isLastPage = false,
        )
    }

    private fun toUser(userDto: UserDto): User = User(
        id = userDto.id,
        email = userDto.email,
        name = "${userDto.first_name} ${userDto.last_name}",
        avatar = userDto.avatar,
    )

    data class State(
        val name: String = "",
        val word: String = "",
        val output: String = "",
        val selected: String = "Selected User Name",
        val users: List<User> = emptyList(),
        val previousPage: Int = 0,
        val isLastPage: Boolean = false,
        val isRefreshing: Boolean = false,
    ) {
        val canNext: Boolean = name.isNotBlank()
        val canCheck: Boolean = word.isNotBlank()
    }
}
