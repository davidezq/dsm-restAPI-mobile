package com.example.apirestproject.menu.ui.professors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apirestproject.Prefs
import com.example.apirestproject.auth.SignInActivity
import com.example.apirestproject.databinding.FragmentProfessorBinding
import com.example.apirestproject.models.Professor
import com.example.apirestproject.professor.CreateProfessorActivity
import com.example.apirestproject.professorlist.ProfessorAdapter

class ProfessorFragment : Fragment() {

    private var _binding: FragmentProfessorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var professors = mutableListOf<Professor>()
    private lateinit var adapter: ProfessorAdapter
    private lateinit var professorViewModel: ProfessorViewModel

    private fun initRecyclerView() {
        adapter = ProfessorAdapter(professors)
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerview.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        professorViewModel =
            ViewModelProvider(this).get(ProfessorViewModel::class.java)

        _binding = FragmentProfessorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerView()

        professorViewModel.onCreate(this.requireContext())

        professorViewModel.professors.observe(viewLifecycleOwner){
            binding.recyclerview.adapter = ProfessorAdapter(it)
        }

        binding.add.setOnClickListener{
            startActivity(Intent(binding.root.context, CreateProfessorActivity::class.java))
        }

        binding.logout.setOnClickListener {
            Prefs(this.requireContext()).wipe()
            startActivity(Intent(this.requireContext(), SignInActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        professorViewModel.onCreate(this.requireContext())
    }
}