package com.example.apirestproject.models

import com.google.gson.annotations.SerializedName

data class Student (
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("age") val age: Long,
    @SerializedName("id") val id: String
)