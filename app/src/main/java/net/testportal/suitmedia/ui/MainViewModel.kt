package net.testportal.suitmedia.ui

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(
) {
    fun getString(id: Int): String = when (id) {
        1 -> "First Screen"
        2 -> "Second Screen"
        3 -> "Third Screen"
        else -> "Palindrome Dialog"
    }
}
