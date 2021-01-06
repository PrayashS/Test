package com.Prayash.week5assignment1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.Prayash.week5assignment1.model.User
import com.Prayash.week5assignment1.module.addPostToServer


class AddPostActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etPost : EditText
    private lateinit var etDescription : EditText
    private lateinit var btnPost : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        binding()
        listener()
    }
    private fun binding()
    {
        etPost = findViewById(R.id.etPost)
        etDescription = findViewById(R.id.etDescription)
        btnPost = findViewById(R.id.btnPost)
    }

    private fun listener()
    {
        btnPost.setOnClickListener(this)
    }

    private fun addPost()
    {
        val detail: User? = intent.getParcelableExtra("userDetail")
        if(TextUtils.isEmpty(etPost.text))
        {
            etPost.error = "Insert Post URL"
            etPost.requestFocus()
        }
        else
        {
            var postDetail:MutableMap<String,String?> = mutableMapOf("username" to detail!!.username,"profile" to detail!!.profileURL,"post" to etPost.text.toString(),"description" to etDescription.text.toString())
            addPostToServer(postDetail)
            clear()
            alert("Post Added","Success")
        }
    }

    private fun alert(msg:String,title:String)
    {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("Ok"){
                dialog: DialogInterface?, which: Int ->
            val detail: User? = intent.getParcelableExtra("userDetail")
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("myData",detail)
            startActivity(intent)
            finish()
        }

        var alertD = builder.create()
        alertD.setCancelable(false)
        alertD.show()
    }

    private fun clear()
    {
        etDescription.text!!.clear()
        etPost.text!!.clear()
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.btnPost ->{
                addPost()
            }
        }
    }
}