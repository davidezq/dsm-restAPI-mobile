package com.example.apirestproject.professor

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Professor
import com.example.apirestproject.net.ProfessorsClient
import com.example.apirestproject.net.RetrofitHelper
import kotlinx.coroutines.launch

class CreateProfessorViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    fun createProfessor(context: Context, professor: Professor) {
        viewModelScope.launch {
            val apiInterface = RetrofitHelper.getRetrofit().create(ProfessorsClient::class.java)
            val call = apiInterface.postProfessor(
                Prefs(context).getJwt(),
                professor
            )
            if (call.isSuccessful) {
                message.postValue("Profesor ${professor.name} ${professor.lastName} creado")
            } else {
                message.postValue("Error al ingresar profesor")
            }
        }
    }
}