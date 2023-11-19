package com.example.mycheck

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,"userdata",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Userdata (name TEXT primary Key, age TEXT, sub TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Userdata")
    }

    fun saveUserData(name :String,age:String,sub:String): Boolean {
        val  p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name",name)
        cv.put("age",age)
        cv.put("sub",sub)
        val result = p0.insert("Userdata",null,cv)
        return result != (-1).toLong()
    }


    fun editUserData(name :String,age:String,sub:String): Boolean {
        val  p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("age",age)
        cv.put("sub",sub)
        val cursor :Cursor = p0.rawQuery("select * from UserData where name = ?", arrayOf(name))
        if (cursor.count>0){
            val result = p0.update("Userdata",cv,"name=?", arrayOf(name))
            return result != -1
        }else{
            return false
        }

    }

    fun deleteUserData(name :String): Boolean {
        val  p0 = this.writableDatabase

        val cursor :Cursor = p0.rawQuery("select * from UserData where name = ?", arrayOf(name))
        if (cursor.count>0){
            val result = p0.delete("Userdata","name=?", arrayOf(name))
            return result != -1
        }else{
            return false
        }

    }

    fun getText(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from Userdata", null)
        return cursor
    }

}