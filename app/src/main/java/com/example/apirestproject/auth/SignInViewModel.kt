package com.example.apirestproject.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apirestproject.Prefs
import com.example.apirestproject.models.Auth
import com.example.apirestproject.net.AuthClient
import com.example.apirestproject.net.RetrofitHelper
import com.example.apirestproject.net.StudentsClient
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {
    private val apiInterface = RetrofitHelper.getRetrofit().create(AuthClient::class.java)

    suspend fun signIn(context: Context, credentials: Auth){
        viewModelScope.launch {
            val call = apiInterface.logIn(credentials)
            if(call.isSuccessful){
                Prefs(context).setJwt(call.body()!!.token)
            }
        }
    }
}