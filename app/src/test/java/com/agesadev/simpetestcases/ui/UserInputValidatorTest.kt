package com.agesadev.simpetestcases.ui

import com.agesadev.simpetestcases.ui.UserInputValidator.validateName
import junit.framework.TestCase.assertEquals
import org.junit.Test


internal class UserInputValidatorTest {

    @Test
    fun `show an error n empty name input`() {
        val name = ""
        assertEquals(false,validateName(name))
    }

    @Test
    fun `show success for valid name`(){
        val name="Agesa"

    }

    @Test
    fun validateEmail() {

    }


    @Test
    fun validateBitmap() {
    }
}