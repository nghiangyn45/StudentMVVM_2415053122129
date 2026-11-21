package com.ute.studentmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ute.studentmvvm.model.Student
import com.ute.studentmvvm.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()

    private val _statusMessage = MutableLiveData<String?>()
    val statusMessage: LiveData<String?> = _statusMessage

    fun insert(student: Student) = viewModelScope.launch {
        try {
            repository.insert(student)
            _statusMessage.value = "Đã lưu sinh viên ${student.fullName}"
        } catch (e: Exception) {
            _statusMessage.value = "Lỗi thêm sinh viên: ${e.message}"
        }
    }

    fun delete(student: Student) = viewModelScope.launch {
        try {
            repository.delete(student)
            _statusMessage.value = "Đã xóa sinh viên ${student.fullName}"
        } catch (e: Exception) {
            _statusMessage.value = "Lỗi xóa sinh viên: ${e.message}"
        }
    }

    fun syncFromCloud() = viewModelScope.launch {
        try {
            repository.syncFromCloud()
            _statusMessage.value = "Đồng bộ dữ liệu thành công"
        } catch (e: Exception) {
            _statusMessage.value = "Không thể đồng bộ: ${e.message ?: "Lỗi mạng"}"
        }
    }
}