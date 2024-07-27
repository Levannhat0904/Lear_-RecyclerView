package com.nhatle.learnrecyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nhatle.learnrecyclerview.utils.Utils

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var imageAvatar: ImageView
    private lateinit var textId: TextView
    private lateinit var textFullName: TextView
    private lateinit var textGpa: TextView
    private lateinit var textGender: TextView
    private lateinit var textEmail: TextView
    private lateinit var textBirthDate: TextView
    private lateinit var textAddress: TextView
    private lateinit var textMajor: TextView
    private lateinit var textYear: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        showStudentDetail()
    }

    private fun setupView() {
        imageAvatar = findViewById(R.id.image_detail_avatar)
        textId = findViewById(R.id.text_detail_id)
        textFullName = findViewById(R.id.text_detail_full_name)
        textGpa = findViewById(R.id.text_detail_gpa)
        textGender = findViewById(R.id.text_detail_gender)
        textEmail = findViewById(R.id.text_detail_email)
        textBirthDate = findViewById(R.id.text_detail_birth_date)
        textAddress = findViewById(R.id.text_detail_address)
        textMajor = findViewById(R.id.text_detail_major)
        textYear = findViewById(R.id.text_detail_year)
    }

    private fun showStudentDetail() {
        val id = intent.getStringExtra(EXTRA_STUDENT_ID)
        val name = intent.getStringExtra(EXTRA_STUDENT_NAME)
        val gender = intent.getStringExtra(EXTRA_STUDENT_GENDER)
        val email = intent.getStringExtra(EXTRA_STUDENT_EMAIL)
        val birthDate = intent.getStringExtra(EXTRA_STUDENT_BIRTH_DATE)
        val address = intent.getStringExtra(EXTRA_STUDENT_ADDRESS)
        val major = intent.getStringExtra(EXTRA_STUDENT_MAJOR)
        val year = intent.getIntExtra(EXTRA_STUDENT_YEAR, 0)
        val gpa = intent.getDoubleExtra(EXTRA_STUDENT_GPA, 0.0)

        val dataId = getString(R.string.id, id)
        val dataFullName = getString(R.string.full_name, name)
        val dataGpa = getString(R.string.gpa, gpa)
        val dataYear = getString(R.string.year, year)
        val dataGender = getString(R.string.gender, gender)
        val dataEmail = getString(R.string.email, email)
        val dataBirthDate = getString(R.string.birth_date, birthDate)
        val dataAddress = getString(R.string.address, address)
        val dataMajor = getString(R.string.major, major)

        textId.text = dataId
        textFullName.text = dataFullName
        textGpa.text = dataGpa
        textYear.text = dataYear
        textGender.text = dataGender
        textEmail.text = dataEmail
        textBirthDate.text = dataBirthDate
        textAddress.text = dataAddress
        textMajor.text = dataMajor
        imageAvatar.setImageResource(Utils.getAvatar(gender!!))
    }

    companion object {
        const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"
        const val EXTRA_STUDENT_NAME = "EXTRA_STUDENT_NAME"
        const val EXTRA_STUDENT_GENDER = "extra_student_gender"
        const val EXTRA_STUDENT_EMAIL = "extra_student_email"
        const val EXTRA_STUDENT_BIRTH_DATE = "extra_student_birth_date"
        const val EXTRA_STUDENT_ADDRESS = "extra_student_address"
        const val EXTRA_STUDENT_MAJOR = "extra_student_major"
        const val EXTRA_STUDENT_YEAR = "extra_student_year"
        const val EXTRA_STUDENT_GPA = "extra_student_gpa"
    }
}
