package com.example.dkotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dkotlinstudy.RoomUtil.RoomDataBase
import com.example.dkotlinstudy.RoomUtil.UserDao
import com.example.dkotlinstudy.RoomUtil.UserEntity

class MainActivity : AppCompatActivity() {

    private lateinit var mUserDao: UserDao


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accessDatabase()
        Thread(insertMemo).start()
    }

    private fun accessDatabase() {
        val userDatabase = RoomDataBase.getInstance(this)!!
        mUserDao = userDatabase.userDao()
    }

    private val insertMemo = Runnable {
        var newUser = UserEntity("김똥깨", "20", "010-1111-5555")
        mUserDao.insert(newUser)
    }
}