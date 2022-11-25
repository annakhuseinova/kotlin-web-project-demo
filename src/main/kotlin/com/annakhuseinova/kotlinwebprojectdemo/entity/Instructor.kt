package com.annakhuseinova.kotlinwebprojectdemo.entity

import javax.persistence.*

@Entity
@Table(name = "Instructors")
data class Instructor(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    @OneToMany(mappedBy = "instructor",
        cascade = [CascadeType.ALL],
        orphanRemoval = true)
    @JoinColumn
    var courses: List<Course> = mutableListOf()
)