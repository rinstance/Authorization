package com.example.authorization

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var password : EditText
    private lateinit var email : EditText
    private lateinit var enter : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isEnter()
        setContentView(R.layout.activity_main)

        password = findViewById(R.id.password)
        email = findViewById(R.id.email)
        enter = findViewById(R.id.enter)
    }

    fun onEnter(view : View) {
        val password = password.text.toString()
        val email = email.text.toString()
        if (isPasswordWroteCorrect(password)) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                val accounts = resources.getStringArray(R.array.accounts)
                val myPreference = MySharedPreferences(this)
                for (account in accounts) {
                    if (email == account.split(" ")[0] && password == account.split(" ")[1]) {
                        val user_name = account.split(" ")[2] + " " + account.split(" ")[3]
                        myPreference.setEmail(email)
                        myPreference.setName(user_name)
                        toAccountActivity(email, user_name)
                    } else {
                        makeToast("Invalid email address or wrong password")
                    }

                }
            } else {
                makeToast("Invalid email")
            }
        }
    }

    private fun isEnter() {
        val myPreference = MySharedPreferences(this)
        if (myPreference.getEmail() == "") return
        toAccountActivity(myPreference.getEmail().toString(), myPreference.getName().toString())
    }

    private fun toAccountActivity(email : String, user_name : String) {
        val accountActivity = Intent(this, AccountActivity::class.java)
        accountActivity.putExtra(AccountActivity.EMAIL, email)
        accountActivity.putExtra(AccountActivity.NAME, user_name)
        startActivity(accountActivity)
    }

    private fun isPasswordWroteCorrect(password : String) : Boolean {
        var isDigit = false
        var isUpper = false
        var isLower = false
        if (password.length >= 6) {
            for (s in password) {
                if (s.isUpperCase())
                    isUpper = true
                if (s.isDigit())
                    isDigit = true
                if (s.isLowerCase())
                    isLower = true
            }
            if (!isDigit || !isLower || !isUpper)
                makeToast("The password must contain at least one uppercase letter, lowercase " +
                        "letter, and number")
        } else {
            makeToast("The password must be longer than 6 characters")
        }
        return (isDigit && isUpper && isLower)
    }

    private fun makeToast(text : String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
