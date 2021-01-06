package com.Prayash.week5assignment1

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.Prayash.week5assignment1.model.User
import com.Prayash.week5assignment1.module.dataAuthenticate
import com.Prayash.week5assignment1.module.getStories
import com.Prayash.week5assignment1.module.user

import kotlin.random.Random

class LoginActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var tvEnrolled : TextView
    private lateinit var etCoventry : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnRegister : Button
    private lateinit var btnToken : Button
    private lateinit var randPerson: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding()
        initialize()
        listener()
    }
    private fun binding()
    {
        tvEnrolled = findViewById(R.id.tvEnrolled)
        etCoventry = findViewById(R.id.etCoventry)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        btnToken = findViewById(R.id.btnToken)

    }

    private fun initialize()
    {
        var data = getStories()
        etCoventry.requestFocus()
        tvEnrolled.text = "Enrolled Users:${data.size}"
    }

    private fun listener()
    {
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
        btnToken.setOnClickListener(this)
    }

    private fun validation():Boolean
    {
        if(TextUtils.isEmpty(etCoventry.text))
        {
            etCoventry.error = "Insert Username"
            etCoventry.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etPassword.text))
        {
            etPassword.error = "Insert Password"
            etPassword.requestFocus()
            return false
        }
        else
        {
            return true
        }
    }

    private fun login()
    {
        if(validation())
        {
            var authentication =
                dataAuthenticate(
                    etCoventry.text.toString(),
                    etPassword.text.toString()
                )
            if(authentication.batchId == "")
            {
                alert("Username or Password incorrect!!","Wrong Data")
                clear()
            }
            else
            {
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("myData",authentication)
                clear()
                startActivity(intent)
            }
        }
    }

    private fun clear()
    {
        etPassword.text!!.clear()
        etCoventry.text!!.clear()
        etCoventry.requestFocus()
    }

    private fun alert(msg:String,title:String)
    {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton("Cancel"){
                dialog: DialogInterface?, which: Int ->
        }

        var alertD = builder.create()
        alertD.setCancelable(false)
        alertD.show()
    }

    private fun createRandomAuth()
    {

        var cap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var overall = cap+cap.toLowerCase()+"0123456789"
        var username = ""
        var password = ""
        while(username.length != 6)
        {
            var index = Random.nextInt(0,overall.length)
            username+=overall[index]
        }

        while(password.length != 6)
        {
            var index = Random.nextInt(0,overall.length)
            password+=overall[index]
        }
        username = "token-"+username
        randPerson = User(
            "",
            "",
            username,
            password,
            "",
            "111111",
            "data:image/jpeg;base64,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"
        )
        etCoventry.setText(username)
        etPassword.setText(password)
        user.add(randPerson)
        tvEnrolled.text = "Enrolled Users:${user.size}"
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.btnRegister->{
                var intent = Intent(this,SignUpActivity::class.java)
                startActivity(intent)

            }
            R.id.btnLogin->{
                login()
            }
            R.id.btnToken ->{
                createRandomAuth()
            }
        }
    }
}