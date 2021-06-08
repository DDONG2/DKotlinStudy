package com.example.dkotlinstudy.RoomUtil

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*데이터베이스를 엑세스 할 수 있으며, Entity와 버전 정보를 관리해줄 Database를 만들어줍니다.
version은 앱을 업데이트하다가 entity의 구조를 변경해야 하는 일이 생겼을 때
이전 구조와 현재 구조를 구분해주는 역할을 한다. 만약 구조가 바뀌었는데 버전이 같다면 에러가 뜨며 디버깅이 되지 않는다.
만약 하나의 데이터 베이스가 여러 개의 entity를 가져야 한다면 arrayOf() 안에 콤마로 구분해서 entity를 넣어주면 된다.
 ex)@Database(entities = arrayOf(User::class, Student::class), version = 1)*/

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

//    val db = Room.databaseBuilder(applicationContext, RoomDataBase::class.java, "database-name").build()

    companion object {

        private val DB_NAME = "doeon_table"


        private var instance: RoomDataBase? = null

        fun getInstance(context: Context) =
            instance?: Room.databaseBuilder(context, RoomDataBase::class.java, DB_NAME).build().also {
                instance = it
            }

    }
}