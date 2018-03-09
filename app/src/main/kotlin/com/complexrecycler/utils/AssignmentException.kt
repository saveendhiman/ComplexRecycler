package com.complexrecycler.utils

/**
 * Created by Saveen on 08/03/18.
 */
class AssignmentException(var code: Int, var serverError: String = "Unknown Error") : RuntimeException() {

    fun getLocalisedString(): String {
        return serverError
    }
}
