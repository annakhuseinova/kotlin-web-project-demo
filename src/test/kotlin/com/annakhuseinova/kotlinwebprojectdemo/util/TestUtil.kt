package com.annakhuseinova.kotlinwebprojectdemo.util

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.entity.Course

fun courseEntityList() = listOf(
    Course(null, "Some Course 1", "Development"),
    Course(null, "Some Course 2", "Development"),
    Course(null, "Some Course 3", "Development")
)
fun courseDto(
    id: Int? = null,
    name: String = "Some Course",
    category: String = "Some Category"
) = CourseDto(
    id,
    name,
    category
)