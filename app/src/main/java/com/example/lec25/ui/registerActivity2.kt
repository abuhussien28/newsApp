package com.example.lec25.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lec25.databinding.ActivityRegister2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase

class registerActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.registerBtn.setOnClickListener {
            val emai=binding.emailSignup.text.toString()
            val password=binding.passwordSignup.text.toString()
            val confrim_pass=binding.conPasswordEt.text.toString()
            if (password!=confrim_pass) Toast.makeText(this, "password donot match", Toast.LENGTH_SHORT).show()
            else if (password.length<6) Toast.makeText(this, "password must contain 6 digts", Toast.LENGTH_SHORT).show()
                 else
                auth.createUserWithEmailAndPassword(emai, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = Firebase.auth.currentUser
                            user!!.sendEmailVerification()
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "e-mail sent ", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Error: ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()


                        }
                    }


        }
        binding.loginBtn.setOnClickListener {
            val inte=Intent(this, loginActivity2::class.java)
            startActivity(inte)
        }

    }
}