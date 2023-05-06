package com.example.apirestproject.professor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.apirestproject.databinding.ActivityProfessorBinding
import com.example.apirestproject.models.Professor

class ProfessorActivity : AppCompatActivity() {
    private val professorViewModel: ProfessorViewModel by viewModels()
    lateinit var binding: ActivityProfessorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // actionBar properties
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // completing the form
        binding.txvName.setText(intent.getStringExtra("name"))
        binding.txvLastName.setText(intent.getStringExtra("lastName"))
        binding.txvAge.setText(intent.getStringExtra("age"))

        // subscriptions
        professorViewModel.message.observe(this, Observer {
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
            val professor = Professor(
                binding.txvName.text.toString(),
                binding.txvLastName.text.toString(),
                binding.txvAge.text.toString().toLong(),
                ""
            )

            professorViewModel.edit(this,intent.getStringExtra("id")!!, professor)
            finish()

        }
    }

    fun showDialog(id: String, name: String, lastName: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Borrar profesor")
            .setMessage("¿Seguro que desea eliminar a $name $lastName?")
            .setPositiveButton("Si") { dialogInterface, it ->
                professorViewModel.delete(
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

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}