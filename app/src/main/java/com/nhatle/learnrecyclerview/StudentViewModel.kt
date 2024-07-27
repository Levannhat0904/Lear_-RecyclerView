package com.nhatle.learnrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentViewModel(
    private val localDataSource: LocalDataSource
) : ViewModel(){
    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students
    fun loadStudents(){
        val students = localDataSource.loadStudents()
        _students.value = students
    }
    class Factory(
        private val localDataSource: LocalDataSource
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudentViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return StudentViewModel(localDataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}