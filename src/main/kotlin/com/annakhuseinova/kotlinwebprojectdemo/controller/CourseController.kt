package com.annakhuseinova.kotlinwebprojectdemo.controller

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid courseDto: CourseDto): CourseDto{
        return courseService.addCourse(courseDto)
    }

    @GetMapping
    fun retrieveAllCourses(): List<CourseDto> {
        return courseService.retrieveAllCourses()
    }

    @GetMapping
    fun retrieveCourseByName(@RequestParam("course_name") courseName: String): List<CourseDto> {
        return courseService.retrieveCourseByName(courseName)
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