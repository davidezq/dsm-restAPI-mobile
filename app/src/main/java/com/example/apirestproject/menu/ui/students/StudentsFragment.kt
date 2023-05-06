package com.example.apirestproject.menu.ui.students

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apirestproject.Prefs
import com.example.apirestproject.studentlist.StudentAdapter
import com.example.apirestproject.auth.SignInActivity
import com.example.apirestproject.databinding.FragmentStudentsBinding
import com.example.apirestproject.models.Student
import com.example.apirestproject.student.CreateStudentActivity

class StudentsFragment : Fragment() {


    private var _binding: FragmentStudentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter
    private lateinit var studentsViewModel: StudentsViewModel


    private fun initRecyclerView() {
        adapter = StudentAdapter(students)
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerview.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        studentsViewModel =
            ViewModelProvider(this).get(StudentsViewModel::class.java)

        _binding = FragmentStudentsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        initRecyclerView()

        studentsViewModel.onCreate(this.requireContext())

        studentsViewModel.students.observe(viewLifecycleOwner) {
            binding.recyclerview.adapter = StudentAdapter(it)
        }

        binding.add.setOnClickListener{
            startActivity(Intent(binding.root.context,CreateStudentActivity::class.java))
        }

        binding.logout.setOnClickListener {
            Prefs(this.requireContext()).wipe()
            startActivity(Intent(this.requireContext(),SignInActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        studentsViewModel.onCreate(this.requireContext())
    }
}