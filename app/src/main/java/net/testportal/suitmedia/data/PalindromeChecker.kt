package net.testportal.suitmedia.data

class PalindromeChecker {

    fun isPalindrome(word: String): Boolean {
        val clear = word.replace(" ", "")
        return clear == clear.reversed()
    }
}
