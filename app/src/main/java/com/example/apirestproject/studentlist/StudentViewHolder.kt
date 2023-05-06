package com.example.apirestproject.studentlist

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestproject.databinding.ItemAlumnoBinding
import com.example.apirestproject.student.StudentActivity

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAlumnoBinding.bind(view)

    fun render(name: String, lastName: String, age: Long, id: String) {
        binding.nombre.text = "Nombre: $name"
        binding.apellido.text = "Apellido: $lastName"
        binding.edad.text = "Edad $age"
        binding.root.setOnClickListener {
            val i = Intent(it.context, StudentActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("lastName", lastName)
            i.putExtra("age", age.toString())
            i.putExtra("id", id)
            it.context.startActivity(i)
//            Toast.makeText(it.context,"Le diste click a $nombre",Toast.LENGTH_LONG).show()
        }
    }
}