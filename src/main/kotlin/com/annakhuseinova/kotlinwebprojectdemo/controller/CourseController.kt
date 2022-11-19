package com.annakhuseinova.kotlinwebprojectdemo.controller

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDto: CourseDto): CourseDto{
        return courseService.addCourse(courseDto)
    }

    @GetMapping
    fun retrieveAllCourses(): List<CourseDto> {
        return courseService.retrieveAllCourses()
    }

    @PutMapping("/{course_id}")
    fun updateCourse(@RequestBody courseDto: CourseDto, @PathVariable("course_id") courseId: Int){
        courseService.updateCourse(courseId, courseDto)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_variable") courseId: Int){
        return courseService.deleteCourse(courseId)
    }
}