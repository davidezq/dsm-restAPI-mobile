package com.example.apirestproject.net

import com.example.apirestproject.models.Auth
import com.example.apirestproject.models.AuthOk
import retrofit2.Response
import retrofit2.http.*

interface AuthClient {
    @POST("auth/login")
    suspend fun logIn(@Body credentials:Auth): Response<AuthOk>

}