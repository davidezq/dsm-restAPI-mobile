package com.example.apirestproject.net

import com.example.apirestproject.models.Student
import com.example.apirestproject.models.Students
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentsClient {
    @GET("students")
    suspend fun getAllStudents(@Header("x-token") token:String ): Response<Students>

    @POST("students")
    suspend fun postStudent(@Header("x-token") token:String, @Body student: Student) : Response<Student>

    @PUT("students/{id}")
    suspend fun putStudent(@Header("x-token") token:String, @Path("id") id:String, @Body student:Student): Response<Student>

    @DELETE("students/{id}")
    suspend fun deleteStudent(@Header("x-token") token:String, @Path("id") id:String): Response<Void>
}