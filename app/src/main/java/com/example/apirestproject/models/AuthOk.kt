package com.example.apirestproject.models

data class AuthOk (
    val user: User,
    val token: String
)

data class User (
    val email: String
)

