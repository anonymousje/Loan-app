package com.example.hello

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hello.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //              BACK BUTTON EVENT

        val backBtn: Button = findViewById(R.id.back_btn)

        backBtn.setOnClickListener {
//            Toast.makeText(this, "Returned Succesfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //              SIGN-IN BUTTON EVENT

//        val signin_btn : Button = findViewById(R.id.sign_in_btn)

        binding.signInBtn.setOnClickListener {

            val email = binding.emailTextField.text.toString()
            val pass = binding.passTextField.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful) {
                        val intent01 = Intent(this, HomeActivity::class.java)
                        startActivity(intent01)
                    }
                    else {
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "Email or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }

        //              SIGN-UP BUTTON EVENT

        val signup_btn : Button = findViewById(R.id.sign_up_link)

        signup_btn.setOnClickListener {
            val intent02 = Intent(this, SignUpActivity::class.java)
            startActivity(intent02)
        }
    }
}