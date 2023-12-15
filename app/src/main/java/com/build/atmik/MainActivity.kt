package com.build.atmik

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.build.atmik.bottoms.SelectFragment

import com.build.atmik.bottoms.UserIFragment

import com.build.atmik.data.DrawableProvider

import com.build.atmik.tops.FaceFragment

// Example: MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        window.setFormat(PixelFormat.RGBA_8888)
        if (sharedPreferences.getInt("gender", -1)==-1) {
            setContentView(R.layout.activity_main)
        }else if(sharedPreferences.getString("name", "").isNullOrBlank()){
            val gender=sharedPreferences.getInt("gender", 1)
            setContentView(R.layout.activity_main)
            val nextFragmentTop = FaceFragment.newInstance(gender+gender*3)
            val nextFragment= UserIFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.top, nextFragmentTop,"faceFragment")
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }else if(sharedPreferences.getInt("costume", -1)==-1){
            Log.d("TAG", "onCreate: ")
            val gender=sharedPreferences.getInt("gender", 1)
            setContentView(R.layout.activity_main)
            val nextFragmentTop = FaceFragment.newInstance(gender+gender*3)
            val nextFragment= UserIFragment()
            val transaction2 = supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.top, nextFragmentTop,"faceFragment")
            transaction2.replace(R.id.bottom, nextFragment)
            transaction2.addToBackStack(null)
            transaction2.commit()
            val nextFragment2 = SelectFragment.newInstance(gender)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.bottom, nextFragment2)
            nextFragmentTop.profile(R.string.select_costume)
            transaction.addToBackStack(null)
            transaction.commit()
        }else{
            clearBacks()
        }
    }


    fun clearBacks(){
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main_init)
        val avatar=findViewById<ImageView>(R.id.avatar)
        val drawable: Drawable? = ContextCompat.getDrawable(this, DrawableProvider.drawables[sharedPreferences.getInt("gender",1)+sharedPreferences.getInt("costume",0)])
        avatar.setImageDrawable(drawable)
    }

}

