package com.complexrecycler.model

/**
 * Created by Saveen on 08/03/18.
 */

data class HerokuappResponse(
		val status: Boolean, //true
		val message: Any, //null
		val data: Data
)

data class Data(
		val users: ArrayList<User>,
		val has_more: Boolean //true
)

data class User(
		val name: String, //User-11
		val image: String, //http://loremflickr.com/300/300?random=11
		val items: ArrayList<String>
)