package com.example.graduationthesis.ui.GUI

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.databinding.ActivityLoginBinding
import com.example.graduationthesis.ui.MainAdminActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {
            loginUser()
        }
        binding.savePass.setOnClickListener {
            saveDataLogin()
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun loginUser() {

        FirebaseDatabase.getInstance().getReference("User")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    val userData = Doc.getValue(User::class.java)
                    val email = binding.emailEt.text.toString()
                    val password = binding.passET.text.toString()
                    if (userData != null && userData.status == "admin" && userData.email == email && userData.passWord == password && email.isNotEmpty() && password.isNotEmpty()) {
                        startActivity(Intent(this, MainAdminActivity::class.java))
//                        Toast.makeText(this, "admin", Toast.LENGTH_LONG).show()
                    } else {
                        startActivity(Intent(this, MainActivity::class.java))
//                        Toast.makeText(this, "user", Toast.LENGTH_LONG).show()
                    }
                    userSignIn = userData
                    break
                }

            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Failed to retrieve user data  ${exception.message}",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
    }

//    override fun onPause() {
//        super.onPause()
//        saveDataLogin()
//    }

    private fun saveDataLogin() {
        sharedPreferences = this.getSharedPreferences("saveDataLogin", Context.MODE_PRIVATE)
        val email = binding.emailEt.text.toString()
        val password = binding.passET.text.toString()
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
        Toast.makeText(
            applicationContext,
            "Save data completed",
            Toast.LENGTH_LONG
        )
            .show()
    }
}

var userSignIn: User? = null


