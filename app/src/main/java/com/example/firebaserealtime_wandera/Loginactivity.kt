package com.example.firebaserealtime_wandera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.  google.firebase.auth.FirebaseAuth

class Loginactivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var login:Button
    lateinit var register:Button

    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        email = findViewById(R.id.edtemaillogin)
        password = findViewById(R.id.edtpasslogin)
        login = findViewById(R.id.btnlog_login)
        register = findViewById(R.id.btnreg_login)

        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {

            //navigate another page
            var gotoreg = Intent(this, Registeractivity::class.java)
            startActivity(gotoreg)

        }

        login.setOnClickListener {
            var arafa = email.text.toString().trim()
            var siri = password.text.toString().trim()

            if (arafa.isEmpty()|| siri.isEmpty()){
                Toast.makeText(this, "one of the inputs is empty", Toast.LENGTH_SHORT).show()
            } else{
                   auth.signInWithEmailAndPassword(arafa,siri).addOnCompleteListener(this){
                       if (it.isSuccessful){
                           Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                           var gotomain = Intent(this, MainActivity::class.java)
                           startActivity(gotomain)
                           finish()
                       }else {
                           Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                       }
                   }
            }
        }



    }
}