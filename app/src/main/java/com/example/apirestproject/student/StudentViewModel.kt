package com.example.apirestproject.student

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Student
import com.example.apirestproject.net.StudentsClient
import com.example.apirestproject.net.RetrofitHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.converter.gson.GsonConverterFactory

class StudentViewModel() : ViewModel() {
    val studentModel = MutableLiveData<Student?>()
    val message = MutableLiveData<String>()

    // retrofit2
    private val apiInterface = RetrofitHelper.getRetrofit().create(StudentsClient::class.java)

    fun edit(context: Context, studentId: String, student: Student) {
        viewModelScope.launch {
            val call = apiInterface.putStudent(
                Prefs(context).getJwt(),
                studentId,
                student
            )
            if (call.isSuccessful) {
                message.postValue("Alumno modificado con exito")
                Log.d("putStudent", "Cambiado con exito ${call.body()}")
            } else {
                message.postValue("Revise todos los campos")
                Log.d("putStudent", "Error :(")
            }
        }
    }

    fun delete(context: Context, studentId: String, name: String, lastName: String) {
        viewModelScope.launch {
            val call = apiInterface.deleteStudent(
                Prefs(context).getJwt(),
                studentId
            )
            if (call.code() == 204) {
                message.postValue("Alumno $name $lastName eliminado")
            } else {
                message.postValue("Error al borrar el Alumno")
                Log.d("deleteStudent", "Error al borrar el alumno")
            }
        }
    }
}