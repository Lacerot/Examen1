package com.example.gpslab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class historiald : AppCompatActivity() {

    lateinit var dbRef: DatabaseReference
    lateinit var jsonText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historiald)

        dbRef = Firebase.database.reference
        jsonText = findViewById<TextView>(R.id.json)


        listar()

        val button2 = findViewById<Button>(R.id.button_salir)

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
        }
    }




    val ubiList = mutableListOf<Marcador>()

    fun listar(){
        val paisesListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val post = dataSnapshot.getValue()
                var paisesText = ""
                for (snapshot in dataSnapshot.children){
                    val p = snapshot.getValue(Marcador::class.java)

                    if (p != null) {
                        Log.i("TEOTEOLS","AGREGANDO A LA LISTA "+p.nombre)
                        ubiList.add(p)
                        paisesText += "${p?.nombre} - ${p?.estado} \n ${p?.fecha} - ${p?.hora} \n \n \n"


                        // primercontador = findViewById(R.id.op1)

                        //  primercontador.setText(p.id_estado)
                    }

                    //Log.i("TEOTEOLS",snapshot.key.toString())
//                    val p = snapshot.child(snapshot.key.toString()).value
//                    Log.i("TEOTEOLS",p.toString())
                }
                jsonText.text = paisesText




            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("TEOTEOLS","CANCELED")
            }
        }
        dbRef.child("marcadores").addValueEventListener(paisesListener)
    }









}