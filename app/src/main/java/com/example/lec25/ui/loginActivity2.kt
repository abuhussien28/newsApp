package com.example.lec25.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lec25.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
binding.loginBtn.setOnClickListener {
    val email=binding.emailLogin.text.toString()
    val password=binding.passwordLogin.text.toString()
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful&&auth.currentUser!!.isEmailVerified) {
                val int=Intent(this, home::class.java)
                startActivity(int)

            }
            else if(!(auth.currentUser!!.isEmailVerified)){
                Toast.makeText(this, "you must verfy your email", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
}
        binding.forgotPassowrdTv.setOnClickListener {
            val emailAddress = "user@example.com"

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}