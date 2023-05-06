package com.example.apirestproject.professor

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Professor
import com.example.apirestproject.net.ProfessorsClient
import com.example.apirestproject.net.RetrofitHelper
import kotlinx.coroutines.launch

class ProfessorViewModel : ViewModel() {
    val professorModel = MutableLiveData<Professor?>()
    val message = MutableLiveData<String>()

    // retrofit2
    private val apiInterface = RetrofitHelper.getRetrofit().create(ProfessorsClient::class.java)

    fun edit(context: Context, professorId: String, professor: Professor) {
        viewModelScope.launch {
            val call = apiInterface.putProfessor(
                Prefs(context).getJwt(),
                professorId,
                professor
            )
            if (call.isSuccessful) {
                message.postValue("Profesor modificado con exito")
                Log.d("putStudent", "Cambiado con exito ${call.body()}")
            } else {
                message.postValue("Revise todos los campos")
                Log.d("putStudent", "Error :(")
            }
        }
    }

    fun delete(context: Context, professorId: String, name: String, lastName: String) {
        viewModelScope.launch {
            val call = apiInterface.deleteProfessor(
                Prefs(context).getJwt(),
                professorId
            )
            if (call.code() == 204) {
                message.postValue("Profesor $name $lastName eliminado")
            } else {
                message.postValue("Error al borrar el profesor")
                Log.d("deleteStudent", "Error al borrar el profesor")
            }
        }
    }
}