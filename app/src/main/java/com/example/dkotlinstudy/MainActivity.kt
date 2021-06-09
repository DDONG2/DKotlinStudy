package com.example.dkotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dkotlinstudy.NetWorkUtil.PersonFromServer
import com.example.dkotlinstudy.NetWorkUtil.RetrofitService
import com.example.dkotlinstudy.RoomUtil.RoomDataBase
import com.example.dkotlinstudy.RoomUtil.UserDao
import com.example.dkotlinstudy.RoomUtil.UserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mUserDao: UserDao


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Room 예제
        accessDatabase()
        Thread(insertMemo).start()

        //Retorfit 예제
        //http://mellowcode.org/json/students/
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        //get 요청
        service.getSuedentList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val personList = response.body()
                    Log.d("retrofit_Success", "res : " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofit_Success", "code : " + code)

                    val header = response.headers()
                    Log.d("retrofit_Success", "header : " + header)

                    val error = response.errorBody()
                }

            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofit_Fail", "good")

            }
        })

        //post 요청  (HashMap)
        val params = HashMap<String, Any>()
        params.put("name", "김개똥")
        params.put("age", "김개똥")
        params.put("intro", "안녕하세요")

        service.createStudent(params).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofit_Success_post", "post : " + person?.name)

                }            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }
        })


        //post 요청2 (객체)
        val person = PersonFromServer(name = "김철수", age = 12, intro = "안녕하세요 김철수 입니다.")

        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofit_Success_post", "post : " + person?.name)

                }            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }
        })






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