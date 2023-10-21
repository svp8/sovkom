package com.june.web.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.june.web.models.*;
import com.june.web.repositories.HomeworkFileRepository;
import com.june.web.repositories.HomeworkRepository;
import com.june.web.repositories.LessonRepository;
import com.june.web.repositories.UserRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private HomeworkFileRepository homeworkFileRepository;

    @GetMapping("/{id}")
    public Homework getById(@PathVariable int id) {
        return homeworkRepository.findById(id).orElse(null);
    }

    @GetMapping("/all")
    public List<Homework> getAll() {
        return homeworkRepository.findAll();
    }
    record HomeworkDto(Integer id, String description, Integer lesson_id, List<AttachedFile> files,
                       LocalDateTime deadline) {
    }
    @PostMapping()
    public Homework createHomework(@RequestBody HomeworkDto homeworkDto) {
        Homework homework=new Homework();
        if(homeworkDto.id()!=null){
            homework=homeworkRepository.findById(homeworkDto.id()).orElse(new Homework());
        }
        if(homeworkDto.files()!=null){
            List<AttachedFile> files=homeworkDto.files;
            for(AttachedFile af:files){
                af.setStep(homework);
            }
            homework.setFiles(files);
        }
        Lesson lesson=lessonRepository.findById(homeworkDto.lesson_id()).orElse(null);
        homework.setLesson(lesson);
        homework.setDescription(homeworkDto.description());
        homework.setDeadline(homeworkDto.deadline());
        return homeworkRepository.save(homework);
    }

    record HomeworkFileDto(Integer id, String description, Integer homework_id, String file,
                       LocalDateTime dateTime, Integer student_id) {
    }

    ;

    @PostMapping("/answer")
    public HomeworkFile createHomeworkFile(@RequestBody HomeworkFileDto homework) {
        HomeworkFile fromDb=null;
        if(homework.id()!=null){
           fromDb=homeworkFileRepository.findById(homework.id()).orElse(null);
        }
        if(fromDb==null){
            fromDb=new HomeworkFile();
        }
        fromDb.setDateTime(homework.dateTime());
        fromDb.setStudent(userRepository.findById(homework.student_id()).orElse(null));
        fromDb.setDescription(homework.description());
        fromDb.setEncodedFile(homework.file());
        fromDb.setHomework(homeworkRepository.findById(homework.homework_id()).orElse(null));
        return homeworkFileRepository.save(fromDb);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable int id) {
//        if(courseService.delete(id)){
//            return  ResponseEntity.ok().body("Deleted successfully");
//        }else return ResponseEntity.notFound().build();
//    }
}
