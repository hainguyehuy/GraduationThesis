package com.example.graduationthesis.ui.GUI

import android.content.Intent
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
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password, "admin")


//                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
//                    if(it.isSuccessful){
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                    else{
//                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
//                    }
//                }
            } else {
                Toast.makeText(this, "Empty field are not allowed", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun loginUser(email: String, password: String, status: String) {
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
//                    user.let {
//                        FirebaseDatabase.getInstance().getReference("User").child(user!!.uid)
//                            .get().addOnSuccessListener { snapShot ->
//                                val userData = snapShot.getValue(User::class.java)
//                                if (userData != null && userData.status == status) {
//                                    Toast.makeText(this, "Welcome ${userData?.name}", Toast.LENGTH_LONG)
//                                        .show()
//                                }
//                            }
//                            .addOnFailureListener { exception ->
//                                Toast.makeText(
//                                    this,
//                                    "Failed to retrieve user data  ${exception.message}",
//                                    Toast.LENGTH_LONG)
//                                    .show()
//                            }
//                    }
//                } else {
//                    Log.e("1234",task.exception.toString())
//                }
//            }
        FirebaseDatabase.getInstance().getReference("User")
            .get().addOnSuccessListener { snapShot ->
                for (Doc in snapShot.children) {
                    val userData = Doc.getValue(User::class.java)
                    if (userData != null) {
                        if (userData.status == "user")
                        {
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        else{
                            startActivity(Intent(this, MainAdminActivity::class.java))
                        }
                        userSignIn = userData
                        break
                    }
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

    override fun onStart() {
        super.onStart()
//        if(firebaseAuth.currentUser!= null){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

}

var userSignIn: User? = null


