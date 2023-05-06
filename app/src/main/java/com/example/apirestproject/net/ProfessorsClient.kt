package com.example.apirestproject.net

import com.example.apirestproject.models.Professor
import com.example.apirestproject.models.Professors
import com.example.apirestproject.models.Student
import com.example.apirestproject.models.Students
import retrofit2.Response
import retrofit2.http.*

interface ProfessorsClient {
    @GET("professors")
    suspend fun getAllProfessors(@Header("x-token") token:String): Response<Professors>

    @POST("professors")
    suspend fun postProfessor(@Header("x-token") token:String, @Body professor: Professor) : Response<Professor>

    @PUT("professors/{id}")
    suspend fun putProfessor(@Header("x-token") token:String, @Path("id") id:String, @Body professor: Professor): Response<Professor>

    @DELETE("professors/{id}")
    suspend fun deleteProfessor(@Header("x-token") token:String, @Path("id") id:String): Response<Void>
}