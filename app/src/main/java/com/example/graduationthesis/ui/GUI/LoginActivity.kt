package com.example.graduationthesis.ui.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
            else {
                Toast.makeText(this,"Empty field are not allowed",Toast.LENGTH_LONG).show()
            }

        }

    }
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser!= null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}