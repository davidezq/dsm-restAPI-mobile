package com.example.apirestproject.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.apirestproject.databinding.ActivityStudentBinding
import com.example.apirestproject.models.Student

class StudentActivity : AppCompatActivity() {
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // actionBar properties
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // completing the form
        binding.txvName.setText(intent.getStringExtra("name"))
        binding.txvLastName.setText(intent.getStringExtra("lastName"))
        binding.txvAge.setText(intent.getStringExtra("age"))

        // subscriptions
        studentViewModel.message.observe(this, Observer {
            showToast(it)
        })


        // view actions
        binding.btnDelete.setOnClickListener {
            showDialog(
                intent.getStringExtra("id")!!,
                intent.getStringExtra("name")!!,
                intent.getStringExtra("lastName")!!
            )

        }

        binding.btnEdit.setOnClickListener {
            val student = Student(
                binding.txvName.text.toString(),
                binding.txvLastName.text.toString(),
                binding.txvAge.text.toString().toLong(),
                ""
            )

            studentViewModel.edit(this,intent.getStringExtra("id")!!, student)
            finish()

        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showDialog(id: String, name: String, lastName: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Borrar estudiante")
            .setMessage("¿Seguro que desea eliminar a $name $lastName?")
            .setPositiveButton("Si") { dialogInterface, it ->
                studentViewModel.delete(
                    this,
                    id,
                    name,
                    lastName
                )
                finish()
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()
            }
            .show()
    }

}