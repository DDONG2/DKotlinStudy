package com.example.dkotlinstudy.RoomUtil

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 컬럼 정보를 저장할 테이블 객체를 만들어줍니다.
//테이블의 이름은 따로 정하지 않으면 클래스 이름을 사용하게 되는데
//만약 테이블 이름을 정해주고 싶다면 @Entity(tableName="userProfile") 이렇게 하면 된다.
@Entity(tableName = "doeon_table")
data class UserEntity(
    var name: String,
    var age: String,
    var phone: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}