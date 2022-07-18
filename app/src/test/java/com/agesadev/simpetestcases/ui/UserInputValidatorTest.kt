package com.agesadev.simpetestcases.ui

import com.agesadev.simpetestcases.ui.UserInputValidator.validateEmail
import com.agesadev.simpetestcases.ui.UserInputValidator.validateName
import junit.framework.TestCase.assertEquals
import org.junit.Test


internal class UserInputValidatorTest {

    @Test
    fun `show an error n empty name input`() {
        val name = ""
        assertEquals(false, validateName(name))
    }

    @Test
    fun `show success for valid name`() {
        val name = "Agesa"
        assertEquals(true, validateName(name))

    }

    @Test
    fun `show error for an empty email field`() {
        val email = ""
        assertEquals(false, validateEmail(email))
    }

    @Test
    fun `show error for an invalid email`() {
        val email = "test"
        assertEquals(false, validateEmail(email))
    }

    @Test
    fun `show success for valid email`() {
        val email = "test@gmail.com"
        assertEquals(true, validateEmail(email))
    }


}