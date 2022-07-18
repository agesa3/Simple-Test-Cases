package com.agesadev.simpetestcases.ui

import android.graphics.Bitmap

object UserInputValidator {

    fun validateName(name: String): Boolean {
        if (name.isEmpty()) {
            return false
        }
        if (name.length > 20) {
            return false
        }

        return true
    }

    fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) {
            return false
        }
        if (email.contains("@").not()) {
            return false
        }
        if (email.contains(".").not()) {
            return false
        }

        return true
    }

    fun validateBitmap(bitmap: Bitmap): Boolean {
        return bitmap.width > 100 && bitmap.height > 100
    }

}