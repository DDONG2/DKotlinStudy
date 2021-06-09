package com.example.dkotlinstudy.NetWorkUtil

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

   /* Request Type
    @GET => 정보 요청
    @POST => 정보 추가 요청
    @DELETE => 정보 삭제 요청
    @PUT => 정보 수정 요청
    */

    @GET("json/students/")
    fun getSuedentList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudent (@Body params : HashMap<String, Any>) : Call<PersonFromServer>

    @POST("json/students/")
    fun createStudentEasy(@Body params : PersonFromServer) : Call<PersonFromServer>



}