package com.swarawan.studentapp.network

import com.swarawan.studentapp.network.request.StudentRequest
import com.swarawan.studentapp.network.response.StudentListResponse
import com.swarawan.studentapp.network.response.StudentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rio Swarawan on 10/26/18.
 */
interface ServiceApi {

    @GET("/api/v1/student/all")
    fun getAll(): Call<StudentListResponse>

    @GET("/api/v1/student/all")
    fun getAllThirdSemester(@Query("category") category: String): Call<StudentListResponse>

    @POST("/api/v1/student/")
    fun insert(@Body request: StudentRequest): Call<StudentResponse>

    @PUT("/api/v1/student/{userId}/{categoryId}")
    fun update(@Path("userId") id: String,
               @Path("categoryId") catId: String,
               @Body request: StudentRequest): Call<StudentListResponse>
}