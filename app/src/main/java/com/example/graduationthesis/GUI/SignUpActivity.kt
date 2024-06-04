package com.example.graduationthesis.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivitySignUpBinding
import com.google.android.play.core.integrity.IntegrityTokenRequest
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass  = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()){
                if(password == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else {
                    Toast.makeText(this,"Password is not match ",Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this,"Empty field are not allowed",Toast.LENGTH_LONG).show()
            }
        }
    }
}