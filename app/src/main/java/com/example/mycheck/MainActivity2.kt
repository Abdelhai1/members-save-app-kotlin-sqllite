package com.example.mycheck

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity2 : AppCompatActivity() {

    private lateinit var etfullName : TextInputEditText
    private lateinit var etage : TextInputEditText
    private lateinit var etsub : TextInputEditText
    private lateinit var save : Button
    private lateinit var db : DBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etfullName = findViewById(R.id.textInputEditText)
        etage = findViewById(R.id.textInputEditText2)
        etsub = findViewById(R.id.textinput3)
        save = findViewById(R.id.button)
        db = DBHelper(this)

        save.setOnClickListener {
            
            val name = etfullName.text.toString().trim()
            val age = etage.text.toString().trim()
            val sub = etsub.text.toString().trim()
            if(name.isEmpty() || age.isEmpty() || sub.isEmpty()){
                if(name.isEmpty()){
                    etfullName.error = "please enter a name"
                }
                if(age.isEmpty()){
                    etage.error = "please enter a name"
                }
                if(sub.isEmpty()){
                    etsub.error = "please enter a name"
                }
            }else{
                val  savedata = db.saveUserData(name,age,sub)
                if(savedata == true){
                    Toast.makeText(this, "Succeed!", Toast.LENGTH_SHORT).show()
                    intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Student already exists!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}