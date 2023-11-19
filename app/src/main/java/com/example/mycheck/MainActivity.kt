package com.example.mycheck

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycheck.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rv : RecyclerView
    private lateinit var button : FloatingActionButton
    private lateinit var dbh : DBHelper
    private lateinit var newArray : ArrayList<Datalist>
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        button = findViewById(R.id.floatingActionButton)

        button.setOnClickListener {
            intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        dbh = DBHelper(this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        displayStudents()

    }

    private fun displayStudents() {
        val newcursor :Cursor? = dbh.getText()
        newArray = ArrayList<Datalist>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val uage = newcursor.getString(1)
            val usub = newcursor.getString(2)
            newArray.add(Datalist(uname,uage,usub))
        }
        adapter =MyAdapter(newArray)
        rv.adapter = adapter
        adapter.onItemClickListener(object :MyAdapter.onItemClickListener{
            override fun OnItemClick(position: Int) {
                val intent = Intent(this@MainActivity,MainActivity3::class.java)
                intent.putExtra("name",newArray[position].name)
                intent.putExtra("age",newArray[position].age)
                intent.putExtra("sub",newArray[position].sub)
                startActivity(intent)
            }

        })

    }
}