package com.annakhuseinova.kotlinwebprojectdemo.controller

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.entity.Course
import com.annakhuseinova.kotlinwebprojectdemo.repository.CourseRepository
import com.annakhuseinova.kotlinwebprojectdemo.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntegrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp(){
        courseRepository.deleteAll()
        val courses = courseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun addCourse(){
        val courseDto = CourseDto(null, "Some Course", "Some Author")
        val responseBody = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody
        Assertions.assertTrue{
            responseBody!!.id != null
        }

    }

    @Test
    fun retrieveAllCourses(){
        val responseBody = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult().responseBody

        println("courseDtos: $responseBody")
        Assertions.assertEquals(3, responseBody!!.size)

    }

    @Test
    fun retrieveCourseByName(){
        val responseBody = webTestClient
            .get()
            .uri("/v1/courses?course_name=Some Course 1")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult().responseBody
        println("Course dto: $responseBody")
        Assertions.assertEquals(1, responseBody!!.size)
    }

    @Test
    fun updateCourse(){
        val course = Course(null, "Some Course", "Development")
        courseRepository.save(course)

        val updateCourseDto = CourseDto(null, "Updated Course", "Development")
        val responseBody = webTestClient
            .put()
            .uri("/v1/courses/{course_id}", course.id)
            .bodyValue(updateCourseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("Updated Course", responseBody!!.name)
    }

    @Test
    fun deleteCourse(){
        val course = Course(null, "Some Course", "Development")
        courseRepository.save(course)
        webTestClient
            .delete()
            .exchange()
            .expectStatus().isNoContent

    }
}