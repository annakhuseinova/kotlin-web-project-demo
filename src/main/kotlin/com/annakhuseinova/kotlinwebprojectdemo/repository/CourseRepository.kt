package com.annakhuseinova.kotlinwebprojectdemo.repository

import com.annakhuseinova.kotlinwebprojectdemo.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Int> {
}