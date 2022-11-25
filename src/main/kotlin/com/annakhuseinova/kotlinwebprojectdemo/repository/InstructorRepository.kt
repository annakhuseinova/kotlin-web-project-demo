package com.annakhuseinova.kotlinwebprojectdemo.repository

import com.annakhuseinova.kotlinwebprojectdemo.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository: CrudRepository<Instructor, Int> {
}