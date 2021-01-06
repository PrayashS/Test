package com.Prayash.week5assignment1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.Prayash.week5assignment1.module.addUser
import com.Prayash.week5assignment1.module.getExist


class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etCoventry : EditText
    private lateinit var etFirstname : EditText
    private lateinit var etLastname : EditText
    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var etURL : EditText
    private lateinit var spinner : Spinner
    private lateinit var btnSignUp : Button
    private lateinit var btnLogin : Button
    var selection = "Select your Batch"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding()
        initialize()
        listener()
    }
    private fun binding()
    {
        etCoventry = findViewById(R.id.etCoventry)
        etFirstname = findViewById(R.id.etFirstname)
        etLastname = findViewById(R.id.etLastname)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etURL = findViewById(R.id.etURL)
        spinner = findViewById(R.id.spinner)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etCoventry.requestFocus()
    }

    private fun initialize()
    {
        var batch = mutableListOf("Select your Batch","25A","25B","25C","25D","25E")
        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,batch)
        spinner.adapter = adapter
    }

    private fun listener()
    {
        btnSignUp.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selection = parent?.getItemAtPosition(position).toString()
                }

            }
    }

    private fun validation():Boolean
    {
        var exists = getExist()
        if(TextUtils.isEmpty(etCoventry.text))
        {
            etCoventry.error = "Insert Coventry Id"
            etCoventry.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etFirstname.text))
        {
            etFirstname.error = "Insert Firstname"
            etFirstname.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etLastname.text))
        {
            etLastname.error = "Insert Lastname"
            etLastname.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etUsername.text))
        {
            etUsername.error = "Insert Username"
            etUsername.requestFocus()
            return false
        }
        else if(selection == "Select your Batch")
        {
            alert("Please select your batch!!","Error")
            return false
        }
        else if(TextUtils.isEmpty(etURL.text))
        {
            etURL.error = "Insert Profile Picture URL"
            etURL.requestFocus()
            return false
        }
        else if(TextUtils.isEmpty(etPassword.text))
        {
            etPassword.error = "Insert Password"
            etPassword.requestFocus()
            return false
        }
        else if(exists["coventry"]!!.contains(etCoventry.text.toString()))
        {
            etCoventry.error = "Coventry Id Already Exists"
            etCoventry.requestFocus()
            return false
        }
        else if(exists["username"]!!.contains(etUsername.text.toString()))
        {
            etUsername.error = "Username Already Exists"
            etUsername.requestFocus()
            return false
        }
        else if(etPassword.text.toString().length < 6)
        {
            etPassword.error = "Password must contain atleast 6 characters"
            etPassword.requestFocus()
            return false
        }
        else
        {
            return true
        }
    }

    private fun alert(msg:String,title:String)
    {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("Ok"){
                dialog: DialogInterface?, which: Int ->
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        var alertD = builder.create()
        alertD.setCancelable(false)
        alertD.show()
    }

    private fun insert()
    {
        if(validation())
        {
            var userDetail : MutableMap<String,String> = mutableMapOf("firstName" to etFirstname.text.toString(), "lastName" to etLastname.text.toString(), "userName" to etUsername.text.toString(), "password" to etPassword.text.toString(), "batch" to selection, "url" to etURL.text.toString(), "coventryId" to etCoventry.text.toString())
            addUser(userDetail)
            clear()
            alert("Successfully Registered!!","Success")
        }
    }

    private fun clear()
    {
        etCoventry.text!!.clear()
        etPassword.text!!.clear()
        etUsername.text!!.clear()
        etFirstname.text!!.clear()
        etLastname.text!!.clear()
        etURL.text!!.clear()
        initialize()
        selection = "Select your Batch"
        etCoventry.requestFocus()
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.btnSignUp->{
                insert()
            }
            R.id.btnLogin ->{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}