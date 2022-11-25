package com.annakhuseinova.kotlinwebprojectdemo.util

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.entity.Course
import com.annakhuseinova.kotlinwebprojectdemo.entity.Instructor

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

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development",
        instructor),
    Course(null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"
        ,instructor
    ),
    Course(null,
        "Wiremock for Java Developers", "Development" ,
        instructor)
)

fun instructorEntity(name : String = "Dilip Sundarraj")
        = Instructor(null, name)
