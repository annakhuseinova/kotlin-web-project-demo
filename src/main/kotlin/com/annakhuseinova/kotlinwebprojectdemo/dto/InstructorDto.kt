package com.annakhuseinova.kotlinwebprojectdemo.dto

import javax.validation.constraints.NotBlank

data class InstructorDto(
    val id: Int?,
    @get:NotBlank(message = "instructorDto.name must not be blank")
    val name: String,

)