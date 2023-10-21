package com.june.web.controllers;

import com.june.web.models.Course;
import com.june.web.models.Lesson;
import com.june.web.models.Module;
import com.june.web.repositories.CourseRepository;
import com.june.web.repositories.LessonRepository;
import com.june.web.repositories.ModuleRepository;
import com.june.web.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {
        return courseService.findById(id);
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        if(courseService.delete(id)){
            return  ResponseEntity.ok().body("Deleted successfully");
        }else return ResponseEntity.notFound().build();
    }


}
