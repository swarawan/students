package com.swarawan.studentapp.network.response

/**
 * Created by Rio Swarawan on 10/26/18.
 */
data class StudentResponse(val status: String,
                           val data: MutableList<StudentData>)