package com.example.apirestproject.professorlist

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.apirestproject.databinding.ItemProfessorBinding
import com.example.apirestproject.professor.ProfessorActivity
import com.example.apirestproject.student.StudentActivity

class ProfessorViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemProfessorBinding.bind(view)

    fun render(name: String, lastName: String, age: Long, id: String){
        binding.nombre.text = "Nombre: $name"
        binding.apellido.text = "Apellido: $lastName"
        binding.edad.text = "Edad $age"

        binding.root.setOnClickListener {
            val i = Intent(it.context, ProfessorActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("lastName", lastName)
            i.putExtra("age", age.toString())
            i.putExtra("id", id)
            it.context.startActivity(i)
        }
    }
}