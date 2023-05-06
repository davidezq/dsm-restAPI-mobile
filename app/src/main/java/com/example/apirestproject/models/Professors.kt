package com.example.apirestproject.models

import com.google.gson.annotations.SerializedName

data class Professors (
    @SerializedName("total") val total: Long,
    @SerializedName("professors") val results: List<Professor>
)