package com.annakhuseinova.kotlinwebprojectdemo.service

import com.annakhuseinova.kotlinwebprojectdemo.dto.InstructorDto
import com.annakhuseinova.kotlinwebprojectdemo.entity.Instructor
import com.annakhuseinova.kotlinwebprojectdemo.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {

    fun createInstructor(instructorDto: InstructorDto): InstructorDto {

        val instructorEntity = instructorDto.let {
            Instructor(it.id, it.name)
        }
        val savedEntity = instructorRepository.save(instructorEntity)
        return savedEntity.let {
            InstructorDto(it.id, it.name)
        }
    }
    fun findByInstructorId(instructorId: Int): Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }
}