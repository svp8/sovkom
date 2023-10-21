package com.june.web.controllers;

import com.june.web.models.*;
import com.june.web.models.Module;
import com.june.web.repositories.LessonRepository;
import com.june.web.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    StepRepository stepRepository;
    @Autowired
    LessonRepository lessonRepository;


    @PostMapping()
    public Lesson createLesson(@RequestBody Lesson lesson) {
        Lesson lessonTemp = lessonRepository.findById(lesson.getId()).orElse(null);
        if (lessonTemp == null) {
            return null;
        }
        List<Step> steps = lesson.getSteps();
        Iterator<Step> itr = steps.iterator();
        Iterator<Step> itr2 = lessonTemp.getSteps().iterator();
        List<Step> forAdd=new ArrayList<>();
        while(itr2.hasNext()){
            itr2.next();
            itr2.remove();
        }
        while(itr.hasNext()){
            Step step=itr.next();
            step.setLesson(lessonTemp);
            for(AttachedFile file:step.getFiles()){
                file.setStep(step);
            }
        }
//        while (itr2.hasNext()) {
//            Step x = itr2.next();
//            boolean flag=false;
//            Step next=null;
//            while (itr.hasNext()) {
//                next=itr.next();
//                if(Objects.equals(x.getId(), next.getId())){
//                    flag=true;
//                    forAdd.add(next);
//                    itr2.remove(); break;
//                }
//            }
//            if(!flag){
//                forAdd.add(next);
//            }
//        }
        lessonTemp.getSteps().addAll(steps);
        return lessonRepository.save(lessonTemp);
    }

    @GetMapping("/{id}")
    public Lesson getById(@PathVariable int id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @GetMapping("/all")
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }
}
