package com.annakhuseinova.kotlinwebprojectdemo.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Table

@Entity
@Table(name = "Courses")
data class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int?,
    var name: String,
    var category: String
)