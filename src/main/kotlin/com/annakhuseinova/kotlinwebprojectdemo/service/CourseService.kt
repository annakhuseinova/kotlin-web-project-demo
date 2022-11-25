package com.annakhuseinova.kotlinwebprojectdemo.service

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.entity.Course
import com.annakhuseinova.kotlinwebprojectdemo.exception.CourseNotFoundException
import com.annakhuseinova.kotlinwebprojectdemo.exception.InstructorNotValidException
import com.annakhuseinova.kotlinwebprojectdemo.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository,
                    val instructorService: InstructorService) {

    companion object: KLogging()

    fun addCourse(courseDto: CourseDto): CourseDto {

        val instructor = instructorService.findByInstructorId(courseDto.instructorId!!)
        if (instructor.isEmpty){
            throw InstructorNotValidException("Instructor not valid for the id: ${courseDto.instructorId}")
        }
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category, instructor.get())
        }
        courseRepository.save(courseEntity)
        logger.info("Saved course is : $courseEntity")
        return courseEntity.let {
            CourseDto(it.id, it.name, it.category, it.instructor!!.id)
        }
    }

    fun retrieveAllCourses(): List<CourseDto> {
        return courseRepository.findAll().map {
            CourseDto(it.id, it.name, it.category)
        }
    }
    fun retrieveCourseByName(courseName: String): List<CourseDto> {
        val coursesByName = courseRepository.findCourseByName(courseName)
        return coursesByName.map { CourseDto(it.id, it.name, it.category) }
    }

    fun updateCourse(courseId: Int, courseDto: CourseDto): CourseDto {
        val existingCourse = courseRepository.findById(courseId)
        return if (existingCourse.isPresent){
            existingCourse.get().let {
                it.name = courseDto.name
                it.category = courseDto.category
                courseRepository.save(it)
                CourseDto(it.id, it.name, it.category)
            }
        } else {
            throw CourseNotFoundException("No course found for id: $courseId")
        }
    }

    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
         if (existingCourse.isPresent){
            existingCourse.get().let {
                courseRepository.deleteById(courseId)
            }
        } else {
             throw CourseNotFoundException("No course found for id: $courseId")
         }
    }
}
