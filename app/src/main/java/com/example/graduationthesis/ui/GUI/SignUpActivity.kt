package com.example.graduationthesis.ui.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {
                    registerUser(email, password, true)
//                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
//                        if(it.isSuccessful){
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                        }
//                        else{
//                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
//                        }
//                    }
                }
                else {
                    Toast.makeText(this,"Password is not match ",Toast.LENGTH_LONG).show()
                }
            }else {
                Toast.makeText(this, "Empty field are not allowed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun registerUser(email: String, passWord: String, status : Boolean) {
        firebaseAuth.createUserWithEmailAndPassword(email, passWord)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user.let {
                        val userStatus = it?.uid
                        val userData = user?.let { it1 -> User(email = email, passWord = passWord, status = "user",id = it1.uid) }
                        userData?.let { it1 ->
                            FirebaseDatabase.getInstance().getReference("User").child(it1.id)
                                .setValue(userData)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Đăng kí tài khoản thành công!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        startActivity(Intent(this, LoginActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Database error: ${dbTask.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}