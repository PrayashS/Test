package com.Prayash.week5assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Prayash.week5assignment1.adapter.PostAdapter
import com.Prayash.week5assignment1.adapter.StoryAdapter
import com.Prayash.week5assignment1.model.PostData
import com.Prayash.week5assignment1.model.StoryData
import com.Prayash.week5assignment1.model.User
import com.Prayash.week5assignment1.module.getPosts
import com.Prayash.week5assignment1.module.getStories
import com.Prayash.week5assignment1.module.user


class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var storyRecycle : RecyclerView
    private lateinit var stories : MutableList<StoryData>
    private lateinit var postRecycle : RecyclerView
    private lateinit var posts:MutableList<PostData>
    private lateinit var btnAdd : View
    private lateinit var btnLogout : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding()
        initialize()
        listener()
    }
    private fun binding()
    {
        storyRecycle = findViewById(R.id.storyRecycle)
        postRecycle = findViewById(R.id.postRecycle)
        btnAdd = findViewById(R.id.btnAdd)
        btnLogout = findViewById(R.id.btnLogout)
    }

    private fun initialize()
    {
        var userIntent: User? = intent.getParcelableExtra("myData")
        stories = mutableListOf(StoryData("You",userIntent!!.profileURL,""))
        var storyData = getStories()
        for(i in storyData)
        {
            if(i.userName != userIntent.username)
            {
                stories.add(i)
            }
        }
        posts = getPosts()

        val adapter = StoryAdapter(this,stories)
        val adapter2 = PostAdapter(this,posts)
        storyRecycle.adapter = adapter
        storyRecycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        postRecycle.adapter = adapter2
        postRecycle.layoutManager = LinearLayoutManager(this)
    }

    private fun listener()
    {
        btnAdd.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var userIntent: User? = intent.getParcelableExtra("myData")

        when(v?.id)
        {
            R.id.btnAdd ->{

                val intent = Intent(this,AddPostActivity::class.java)
                intent.putExtra("userDetail",userIntent)
                startActivity(intent)



            }
            R.id.btnLogout->{
                var totalData = user.filter {
                    it.username!!.startsWith("token")
                }
                var totalData2 = posts.filter {
                    it.un!!.startsWith("token")
                }
                for(i in totalData)
                {
                    user.removeAt(user.indexOf(i))
                }
                for(j in totalData2)
                {
                    posts.removeAt(posts.indexOf(j))
                }

                var intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}