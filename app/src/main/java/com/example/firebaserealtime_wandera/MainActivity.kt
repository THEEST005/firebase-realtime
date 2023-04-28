package com.example.firebaserealtime_wandera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var carmake:EditText
    lateinit var carmodel:EditText
    lateinit var carprice:EditText
    lateinit var carphoto:Button
    lateinit var cardata:Button
    lateinit var uploadedcars:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carmake = findViewById(R.id.edtcarmake)
        carmodel = findViewById(R.id.edtcarmodel)
        carprice = findViewById(R.id.edtcarprice)
        carphoto = findViewById(R.id.btnphoto)
        cardata = findViewById(R.id.btncardata)
        uploadedcars = findViewById(R.id.btnuploaded)


        //initialise
        var database = FirebaseDatabase.getInstance()
        var databaseRef = database.getReference("cars")

        cardata.setOnClickListener {
            var edt_carmake = carmake.text.toString().trim()
            var edt_carmodel = carmodel.text.toString().trim()
            var edt_carprice = carprice.text.toString().trim()

            //validate entries
            if (edt_carmake.isEmpty()|| edt_carmodel.isEmpty()|| edt_carprice.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            } else{
                //saving info to firebase
                var usercar = Car(edt_carmake,edt_carmodel,edt_carprice)
                var ref = FirebaseDatabase.getInstance().getReference().child("cars")

                ref.setValue(usercar).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Car Data Uploaded successfully", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this, "Failed to save catr info", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }



    }
}