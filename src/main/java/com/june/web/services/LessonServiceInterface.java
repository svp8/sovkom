package com.june.web.services;

import com.june.web.models.Course;
import com.june.web.models.Lesson;

import java.util.List;

public interface LessonServiceInterface {
    Lesson findById(int id);
    List<Lesson> findAll();
    Lesson createLesson(Lesson lesson);

    boolean delete(int id);
}
