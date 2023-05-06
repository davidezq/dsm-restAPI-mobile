package com.example.apirestproject.studentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestproject.R
import com.example.apirestproject.models.Student

class StudentAdapter(val alumnos: List<Student>) : RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StudentViewHolder(layoutInflater.inflate(R.layout.item_alumno, parent, false))
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = alumnos[position]
        holder.render(item.name, item.lastName, item.age, item.id)
    }

    override fun getItemCount(): Int {
        return alumnos.size
    }
}