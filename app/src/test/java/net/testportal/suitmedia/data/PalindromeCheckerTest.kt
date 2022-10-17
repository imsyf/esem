package net.testportal.suitmedia.data

import org.junit.Assert.assertEquals
import org.junit.Test

class PalindromeCheckerTest {

    private val palindromeChecker = PalindromeChecker()

    @Test
    fun `kasur rusak is a palindrome`() {
        val actual = palindromeChecker.isPalindrome("kasur rusak")
        assertEquals(true, actual)
    }

    @Test
    fun `step on no pets is a palindrome`() {
        val actual = palindromeChecker.isPalindrome("step on no pets")
        assertEquals(true, actual)
    }

    @Test
    fun `put it up is a palindrome`() {
        val actual = palindromeChecker.isPalindrome("put it up")
        assertEquals(true, actual)
    }

    @Test
    fun `suitmedia is not a palindrome`() {
        val actual = palindromeChecker.isPalindrome("suitmedia")
        assertEquals(false, actual)
    }

    @Test
    fun `abc xyz is not a palindrome`() {
        val actual = palindromeChecker.isPalindrome("abc xyz")
        assertEquals(false, actual)
    }
}
