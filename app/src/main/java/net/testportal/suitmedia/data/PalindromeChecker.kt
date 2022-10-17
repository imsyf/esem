package net.testportal.suitmedia.data

class PalindromeChecker {

    fun isPalindrome(word: String): Boolean {
        val nospace = word.replace(" ", "")

        for (i in 0 until (nospace.lastIndex - 1) / 2) {
            val front = nospace[i]
            val back = nospace[nospace.lastIndex - i]

            if (front != back) return false
        }

        return true
    }
}
