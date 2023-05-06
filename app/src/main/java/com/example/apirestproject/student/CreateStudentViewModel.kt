package com.example.apirestproject.student

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Student
import com.example.apirestproject.net.RetrofitHelper
import com.example.apirestproject.net.StudentsClient
import kotlinx.coroutines.launch

class CreateStudentViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun createStudent(context: Context, student:Student){
        viewModelScope.launch {
            val apiInterface = RetrofitHelper.getRetrofit().create(StudentsClient::class.java)
            val call = apiInterface.postStudent(
                Prefs(context).getJwt(),
                student )
            if(call.isSuccessful){
                message.postValue("Alumno ${student.name} ${student.lastName} creado")
            } else
            {
                message.postValue("Error al ingresar usuario")
            }
        }
    }
}