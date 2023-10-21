package com.june.web.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.june.web.models.Lesson;
import com.june.web.models.Module;
import com.june.web.models.Step;
import com.june.web.repositories.StepRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    StepRepository stepRepository;
    public void saveLesson(){
    }
}
