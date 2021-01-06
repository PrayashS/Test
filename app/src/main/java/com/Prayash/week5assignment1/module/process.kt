package com.Prayash.week5assignment1.module

import com.Prayash.week5assignment1.model.PostData
import com.Prayash.week5assignment1.model.StoryData
import com.Prayash.week5assignment1.model.User


var user:ArrayList<User> = arrayListOf(User("admin","admin","admin","admin","25C","345678","https://www.clker.com/cliparts/d/L/P/X/z/i/no-image-icon-md.png"))
var posts: ArrayList<PostData> = arrayListOf()
var stories : ArrayList<StoryData> = arrayListOf(StoryData("admin","https://www.clker.com/cliparts/d/L/P/X/z/i/no-image-icon-md.png",""))
fun addUser(detail:MutableMap<String,String>)
{
    val userVal = User(detail["firstName"],detail["lastName"],detail["userName"],detail["password"],detail["batch"],detail["coventryId"],detail["url"])
    user.add(userVal)
    val storyVal = StoryData(detail["userName"],detail["url"],"")
    stories.add(storyVal)
}

fun getStories():MutableList<StoryData>
{
    stories.reverse()
    return stories
}

fun getPosts():MutableList<PostData>
{
    posts.reverse()
    return posts.toMutableList()
}

fun getExist():MutableMap<String,List<String?>>
{

    var userNameList = user.map {
        it.username
    }
    var coventryList = user.map {
        it.batchId
    }

    var holder:MutableMap<String,List<String?>> = mutableMapOf("username" to userNameList, "coventry" to coventryList)
    return holder
}

fun dataAuthenticate(uname:String,password:String):User
{
    var users = User("","","","","","","")
    for(i in user)
    {
        if(i.username == uname && i.password == password)
        {

            users = i
            break
        }
    }
    return users
}

fun addPostToServer(postDetail:MutableMap<String,String?>)
{
    val post = PostData(postDetail["profile"],postDetail["username"],postDetail["post"],postDetail["description"])
    posts.add(post)
}