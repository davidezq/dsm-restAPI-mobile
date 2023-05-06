package com.example.apirestproject.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import com.example.apirestproject.Prefs
import com.example.apirestproject.databinding.ActivitySignInBinding
import com.example.apirestproject.menu.MainActivity
import com.example.apirestproject.models.Auth
import com.example.apirestproject.net.AuthClient
import com.example.apirestproject.net.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignInActivity : AppCompatActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActionBar config
        title=""
        supportActionBar?.hide()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            var errors = 0
            if(binding.txvEmail.text.toString().isEmpty()){
                binding.txvEmail.error = "Ingrese un correo"
                errors++
            }
            if(binding.txvPasswod.text.toString().isEmpty()){
                binding.txvPasswod.error = "Ingrese un password válido"
                errors++
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(binding.txvEmail.text.toString()).matches()){
                binding.txvEmail.error = "Ingrese un correo válido"
                errors++
            }
            if(errors != 0){
                return@setOnClickListener
            }

            val apiInterface = RetrofitHelper.getRetrofit().create(AuthClient::class.java)

            CoroutineScope(Dispatchers.Main).launch {
                val call = apiInterface.logIn(Auth(
                    binding.txvEmail.text.toString(),
                    binding.txvPasswod.text.toString()
                ))
                if(call.isSuccessful){
                    Prefs(it.context).setJwt(call.body()!!.token)
                }
                withContext(Dispatchers.Main){
                    val i = Intent(it.context,MainActivity::class.java)
                    startActivity(i)
                }
            }


        }
    }

    override fun onBackPressed() {
    }
}