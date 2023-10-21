package com.june.web.services;

import com.june.web.models.Course;

import java.util.List;

public interface CourseServiceInterface {
    Course findById(int id);
    List<Course> findAll();
    Course createCourse(Course course);
    boolean delete(int id);

}
