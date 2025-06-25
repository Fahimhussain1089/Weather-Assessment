package com.hussain.weatherassessmenttest.Activity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hussain.weatherassessmenttest.data.User
import com.hussain.weatherassessmenttest.databinding.ActivityUserFormBinding
import com.hussain.weatherassessmenttest.viewmodel.UserViewModel


class UserFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserFormBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupClickListeners()
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {
            saveUser()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveUser() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        // Reset errors
        binding.tilFirstName.error = null
        binding.tilLastName.error = null
        binding.tilEmail.error = null

        var isValid = true

        if (firstName.isEmpty()) {
            binding.tilFirstName.error = "First name is required"
            isValid = false
        }

        if (lastName.isEmpty()) {
            binding.tilLastName.error = "Last name is required"
            isValid = false
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Enter a valid email address"
            isValid = false
        }

        if (isValid) {
            val user = User(
                firstName = firstName,
                lastName = lastName,
                email = email
            )

            userViewModel.insertUser(user)
            Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}