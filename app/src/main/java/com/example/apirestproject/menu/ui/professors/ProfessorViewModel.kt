package com.example.apirestproject.menu.ui.professors

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Professor
import com.example.apirestproject.models.Student
import com.example.apirestproject.net.ProfessorsClient
import com.example.apirestproject.net.RetrofitHelper
import com.example.apirestproject.net.StudentsClient
import kotlinx.coroutines.launch

class ProfessorViewModel : ViewModel() {

    val professors = MutableLiveData<List<Professor>>()
    private val apiInterface = RetrofitHelper.getRetrofit().create(ProfessorsClient::class.java)

    fun onCreate(context: Context){
        viewModelScope.launch {
            val call = apiInterface.getAllProfessors(
                token = Prefs(context).getJwt()
            )
            if(call.isSuccessful){
                val apiProfessor = call.body()
                professors.postValue(apiProfessor?.results)
            }else{

            }
        }
    }
}