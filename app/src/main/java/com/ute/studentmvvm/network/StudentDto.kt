package com.ute.studentmvvm.network

import com.ute.studentmvvm.model.Student

data class StudentDto(
    val id: Int = 0,
    val fullName: String,
    val studentCode: String,
    val email: String,
    val gpa: Double,
    val major: String = "Công Nghệ Thông Tin"
)

fun StudentDto.toEntity(): Student {
    return Student(
        id = 0,
        fullName = fullName,
        studentCode = studentCode,
        email = email,
        gpa = gpa,
        major = major
    )
}