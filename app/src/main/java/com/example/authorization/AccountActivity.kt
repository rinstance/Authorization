package com.example.authorization

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class AccountActivity : AppCompatActivity() {

    private lateinit var email_view : TextView
    private lateinit var name : TextView
    private lateinit var photo : ImageView
    private lateinit var logout : Button

    companion object {
        const val EMAIL = "email"
        const val NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        email_view = findViewById(R.id.email_view)
        name = findViewById(R.id.name)
        photo = findViewById(R.id.photo)
        logout = findViewById(R.id.logout)

        val email = intent.getStringExtra(EMAIL)
        val user_name = intent.getStringExtra(NAME)

        setPhoto(email)

        email_view.text = ("E-mail: " + email)
        name.text = ("Name: " + user_name)
    }

    fun onLogOut(view : View) {
        val mainActivity = Intent(this, MainActivity::class.java)
        val myPreference = MySharedPreferences(this)
        myPreference.setEmail("")
        startActivity(mainActivity)
    }

    private fun setPhoto(email : String) {
        when (email.split("@")[0]) {
            "damir" -> photo.setImageResource(R.drawable.damir)
            "danil" -> photo.setImageResource(R.drawable.danil)
            "rinat" -> photo.setImageResource(R.drawable.rinat)
            "rustem" -> photo.setImageResource(R.drawable.rustem)
        }
    }
}
