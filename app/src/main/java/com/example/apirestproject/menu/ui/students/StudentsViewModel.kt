package com.example.apirestproject.menu.ui.students

import android.content.Context
import androidx.lifecycle.*
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Student
import com.example.apirestproject.net.StudentsClient
import com.example.apirestproject.net.RetrofitHelper
import kotlinx.coroutines.launch

class StudentsViewModel : ViewModel() {

    val students = MutableLiveData<List<Student>>()
    private val apiInterface = RetrofitHelper.getRetrofit().create(StudentsClient::class.java)

    fun onCreate(context: Context){
        viewModelScope.launch {
            val call = apiInterface.getAllStudents(
                token = Prefs(context).getJwt()
            )
            if(call.isSuccessful){
                val apiStudents = call.body()
                students.postValue(apiStudents?.results)
            }else{

            }
        }
    }
}