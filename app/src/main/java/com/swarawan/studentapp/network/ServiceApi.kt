package com.swarawan.studentapp.network

import com.swarawan.studentapp.network.response.StudentResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Rio Swarawan on 10/26/18.
 */
interface ServiceApi {

    @GET("/api/v1/student/all")
    fun getAll(): Call<StudentResponse>
}