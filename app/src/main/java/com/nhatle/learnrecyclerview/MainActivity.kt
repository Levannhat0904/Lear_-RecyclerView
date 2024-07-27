package com.nhatle.learnrecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerStudent: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        recyclerStudent = findViewById(R.id.rv_student)
        studentAdapter = StudentAdapter(object : StudentAdapter.OnStudentClickListener {
            override fun onItemClick(student: Student) {
//                Toast.makeText(this@MainActivity, student.fullName.toString(), Toast.LENGTH_SHORT).show()
                navigateToDetail(student)
            }
        })
        recyclerStudent.adapter = studentAdapter
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerStudent.addItemDecoration(divider)
    }
    private fun navigateToDetail(student: Student) {
        val intent = Intent(this, StudentDetailActivity::class.java)
//        const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"
//        const val EXTRA_STUDENT_NAME = "EXTRA_STUDENT_NAME"
//        const val EXTRA_STUDENT_GENDER = "extra_student_gender"
//        const val EXTRA_STUDENT_EMAIL = "extra_student_email"
//        const val EXTRA_STUDENT_BIRTH_DATE = "extra_student_birth_date"
//        const val EXTRA_STUDENT_ADDRESS = "extra_student_address"
//        const val EXTRA_STUDENT_MAJOR = "extra_student_major"
//        const val EXTRA_STUDENT_YEAR = "extra_student_year"
//        const val EXTRA_STUDENT_GPA = "extra_student_gpa"
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_ID, student.id)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_NAME, student.fullName.toString())
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_GENDER, student.gender)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_EMAIL, student.email)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_BIRTH_DATE, student.birthDate)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_ADDRESS, student.address)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_MAJOR, student.major)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_YEAR, student.year)
        intent.putExtra(StudentDetailActivity.EXTRA_STUDENT_GPA, student.gpa)
        startActivity(intent)
    }
    private fun setupViewModel() {
        val dataSource = LocalDataSource(this)
        val viewModelFactory = StudentViewModel.Factory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[StudentViewModel::class.java]
        viewModel.loadStudents()
        viewModel.students.observe(this) {
            studentAdapter.updateData(it)
        }
    }
}