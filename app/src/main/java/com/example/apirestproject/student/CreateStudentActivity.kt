package com.example.apirestproject.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.apirestproject.databinding.ActivityCreateStudentBinding
import com.example.apirestproject.databinding.ActivityStudentBinding
import com.example.apirestproject.models.Student
import com.example.apirestproject.net.RetrofitHelper
import com.example.apirestproject.net.StudentsClient
import kotlinx.coroutines.*

class CreateStudentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCreateStudentBinding
    private val createStudentViewModel : CreateStudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ActionBar config
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // subscriptions
        createStudentViewModel.message.observe(this, Observer {
            showToast(it)
        })

        // view actions
        binding.btnCreate.setOnClickListener{
            val student = Student(
                binding.txvName.text.toString(),
                binding.txvLastName.text.toString(),
                binding.txvAge.text.toString().toLong(),
                ""
            )
            try {
                createStudentViewModel.createStudent(this,student)
                finish()
            } catch (e:Exception){
                throw Exception(e)
            }
        }

    }


    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}