package com.example.apirestproject.professorlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestproject.R
import com.example.apirestproject.models.Professor


class ProfessorAdapter(private val professors: List<Professor>) : RecyclerView.Adapter<ProfessorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProfessorViewHolder(layoutInflater.inflate(R.layout.item_professor,parent,false))
    }

    override fun onBindViewHolder(holder: ProfessorViewHolder, position: Int) {
        val item = professors[position]
        holder.render(item.name,item.lastName,item.age,item.id)
    }

    override fun getItemCount(): Int {
        return professors.size
    }
}