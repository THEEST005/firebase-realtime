package com.example.firebaserealtime_wandera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var data_edt:EditText
    lateinit var save_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data_edt = findViewById(R.id.edt_data)
        save_btn = findViewById(R.id.btn_save)

        //initialise
        var database = FirebaseDatabase.getInstance()
        var databaseRef = database.reference


        save_btn.setOnClickListener {
            var user_data = data_edt.text.toString().trim()
           // Toast.makeText(this, user_data, Toast.LENGTH_SHORT).show()
            databaseRef.setValue(user_data)
        }
    }
}