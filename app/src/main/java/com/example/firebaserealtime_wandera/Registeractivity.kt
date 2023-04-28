package com.example.firebaserealtime_wandera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Registeractivity : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var createaccount:Button

    //initialise firebase
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeractivity)

        name = findViewById(R.id.edtname_reg)
        email = findViewById(R.id.edtemail_reg)
        password = findViewById(R.id.edtpass_reg)
        createaccount = findViewById(R.id.btncreate_reg)

        //initialise firebase again
        auth = FirebaseAuth.getInstance()

        createaccount.setOnClickListener {
            var edtname = name.text.toString().trim()
            var edtemail = email.text.toString().trim()
            var edtpassword = password.text.toString().trim()

            //validate inputs


            if (edtname.isEmpty()||edtemail.isEmpty()||edtpassword.isEmpty()){
                Toast.makeText(this, "Invalid Entry!", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(edtemail, edtpassword).addOnCompleteListener(this){
                    if(it.isSuccessful){
                        Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                        var gotolog = Intent(this, Loginactivity::class.java)
                        startActivity(gotolog)
                        finish()
                    }else{



                        Toast.makeText(this, "Failed to Create Account.", Toast.LENGTH_SHORT).show()

                        Log.d("TAG", "error------>", it.exception)
                    }
                }


            }

        }
    }
}