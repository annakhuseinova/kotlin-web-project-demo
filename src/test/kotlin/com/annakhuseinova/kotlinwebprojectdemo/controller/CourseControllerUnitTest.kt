package com.annakhuseinova.kotlinwebprojectdemo.controller

import com.annakhuseinova.kotlinwebprojectdemo.dto.CourseDto
import com.annakhuseinova.kotlinwebprojectdemo.service.CourseService
import com.annakhuseinova.kotlinwebprojectdemo.util.courseDto
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMock: CourseService

    @Test
    fun addCourse(){
        val courseDto = CourseDto(null, "Some Course", "Some Category")

        every { courseServiceMock.addCourse(any()) } returns CourseDto(1, "Some Course", "Some Category")
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
    fun addCourseValidation(){
        val courseDto = CourseDto(null, "", "")

        every { courseServiceMock.addCourse(any()) } returns CourseDto(1, "Some Course", "Some Category")
        val responseBody = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isBadRequest

    }

    @Test
    fun retrieveAllCourses(){
        every { courseServiceMock.retrieveAllCourses() }.returnsMany(
            listOf(
                courseDto(id = 1), courseDto(id =  2)
            )
        )
        val responseBody = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult().responseBody

        println("courseDtos: $responseBody")
        Assertions.assertEquals(2, responseBody!!.size)
    }
}