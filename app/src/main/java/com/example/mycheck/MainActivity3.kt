package com.example.mycheck

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private  lateinit var etName :EditText
    private lateinit var etAge :EditText
    private lateinit var etSub :EditText
    private lateinit var delete : ImageView
    private lateinit var edit : ImageView
    private lateinit var db : DBHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        etName = findViewById(R.id.editTextText)
        etAge = findViewById(R.id.editTextText2)
        etSub = findViewById(R.id.editTextText3)
        delete = findViewById(R.id.imageView4)
        edit = findViewById(R.id.imageView5)
        db = DBHelper(this)

        etName.setText(intent.getStringExtra("name"))
        etAge.setText(intent.getStringExtra("age"))
        etSub.setText(intent.getStringExtra("sub"))

        delete.setOnClickListener {
            if(!db.deleteUserData(etName.text.toString())){
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "deleted Successfully", Toast.LENGTH_SHORT).show()
                intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        edit.setOnClickListener {
            if (etAge.text.toString() == intent.getStringExtra("age") && etSub.text.toString() ==intent.getStringExtra("sub") ){
                Toast.makeText(this, "You didn't change anything!", Toast.LENGTH_SHORT).show()
            }else{
                if(!db.editUserData(etName.text.toString(),etAge.text.toString(),etSub.text.toString())){
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "edited Successfully", Toast.LENGTH_SHORT).show()
                    intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}