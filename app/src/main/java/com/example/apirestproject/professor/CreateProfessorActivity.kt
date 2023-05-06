package com.example.apirestproject.professor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.apirestproject.databinding.ActivityCreateProfessorBinding
import com.example.apirestproject.menu.ui.professors.ProfessorViewModel
import com.example.apirestproject.models.Professor
import com.example.apirestproject.student.CreateStudentViewModel

class CreateProfessorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateProfessorBinding
    private val createProfessorViewModel : CreateProfessorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ActionBar config
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // subscriptions
        createProfessorViewModel.message.observe(this, Observer {
            showToast(it)
        })

        // view actions
        binding.btnCreate.setOnClickListener{
            val professor = Professor(
                binding.txvName.text.toString(),
                binding.txvLastName.text.toString(),
                binding.txvAge.text.toString().toLong(),
                ""
            )
            try {
                createProfessorViewModel.createProfessor(this,professor)
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