package com.example.dkotlinstudy.RoomUtil

import androidx.room.*

//데이터베이스에 접근 가능한 쿼리를 제공해주는 Dao를 만들어줍니다.
@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM doeon_table") // 테이블의 모든 값을 가져와라
    fun getAll(): List<UserEntity>

    @Query("DELETE FROM doeon_table WHERE name = :name") // 'name'에 해당하는 유저를 삭제해라
    fun deleteUserByName(name: String)


    //OnConflictStrategy.REPLACE는 Insert 할때 PrimaryKey가 겹치는 것이 있으면 덮어 쓴다는 의미
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserEntity)
}