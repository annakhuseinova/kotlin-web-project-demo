package com.annakhuseinova.kotlinwebprojectdemo.interoperability;

import com.annakhuseinova.kotlinwebprojectdemo.entity.Course;
import com.annakhuseinova.kotlinwebprojectdemo.entity.Instructor;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;

import java.util.Collections;

public class InvokedKotlinFromJava {

    @JvmStatic
    @JvmOverloads
    public static void main(String[] args) {
        Instructor instructor = new Instructor(1, "whatever", Collections.emptyList());
        new Course(1, "Some Course Description", "Some category", instructor);
    }
}
