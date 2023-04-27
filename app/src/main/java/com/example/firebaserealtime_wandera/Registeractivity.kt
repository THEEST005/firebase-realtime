package com.example.firebaserealtime_wandera

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
            var address = email.text.toString().trim()
            var access = password.text.toString().trim()

            //validate inputs
            if (edtname.isEmpty()|| address.isEmpty()|| access.isEmpty()){
                Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show()
            } else{
                //create a user in firebase
                auth.createUserWithEmailAndPassword(address,access).addOnCompleteListener(this){

                    if (it.isSuccessful){
                        Toast.makeText(this, "user created successfully", Toast.LENGTH_SHORT).show()
                    } else{



                        Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show()

                        Log.d("TAG", "ERROR===>", it.exception)
                    }

                }
            }

        }
    }
}