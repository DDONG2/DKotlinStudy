package com.example.dkotlinstudy.NetWorkUtil

import java.io.Serializable

class PersonFromServer (val id : Int? = null,
                        val name :String? = null,
                        val age: Int? = null,
                        var intro: String? = null) : Serializable

