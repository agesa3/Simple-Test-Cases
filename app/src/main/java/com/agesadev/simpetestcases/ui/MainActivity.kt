package com.agesadev.simpetestcases.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.agesadev.simpetestcases.R
import com.agesadev.simpetestcases.databinding.ActivityMainBinding
import com.agesadev.simpetestcases.model.UserBody

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val name = binding.etName.text.toString()
            if (UserInputValidator.validateEmail(email).not()) {
                return@setOnClickListener
            }
            if (UserInputValidator.validateName(name).not()) {
                return@setOnClickListener
            }
            mainActivityViewModel.createUser(UserBody(name, email))
        }
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                if (UserInputValidator.validateBitmap(bitmap).not()) {
                    return@registerForActivityResult
                }
                binding.ivImg.setImageBitmap(bitmap)
            }

        binding.ivImg.setOnClickListener {
            getContent.launch("image/*")
        }
    }
}