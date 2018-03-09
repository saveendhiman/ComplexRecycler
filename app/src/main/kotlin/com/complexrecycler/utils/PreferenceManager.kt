package com.complexrecycler.utils

import android.content.Context
import android.content.SharedPreferences
import com.complexrecycler.constants.PrefernceConstants

/**
 * Created by Saveen on 08/03/18.
 * Contains method to store and retrieve SharedPreferences data
 */
class PreferenceManager(context: Context) {

    init {
        Companion.context = context
    }

    //get shared pref
    private val preferences: SharedPreferences
        get() = context!!.getSharedPreferences(PrefernceConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    // returns user id
    //Set user id
    var userID: String
        get() = preferences.getString(PrefernceConstants.USER_ID, "")
        set(userid) {
            preferences.edit().putString(PrefernceConstants.USER_ID, userid).apply()
        }

    companion object {

        var context: Context? = null
            private set
    }

}