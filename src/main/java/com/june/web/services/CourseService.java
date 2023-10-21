package com.june.web.services;

import com.june.web.models.Course;
import com.june.web.models.Lesson;
import com.june.web.models.Module;
import com.june.web.repositories.CourseRepository;
import com.june.web.repositories.LessonRepository;
import com.june.web.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CourseService implements CourseServiceInterface {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        //check if exists
        Course temp = courseRepository.findById(course.getId() != null ? course.getId() : -1).orElse(null);
        if (temp == null) {
            //create temp course without arrays
            temp = new Course(course.getCreator(), course.getTitle(), course.getDescription());
            temp = courseRepository.save(temp);
            createModules(course.getModules(), temp);
        } else {
            //TODO :temp.getStudents();
            List<Module> forSave = course.getModules();
            Iterator<Module> itr = temp.getModules().iterator();
            while (itr.hasNext()) {
                Module x = itr.next();
                Iterator<Module> newValues = forSave.iterator();
                boolean flag = false;
                while (newValues.hasNext()) {
                    Module val = newValues.next();
                    if (Objects.equals(val.getId(), x.getId())) {
                        flag = true;
                        break;
                    }

                }
                if (!flag) {
                    moduleRepository.deleteById(x.getId());
                    itr.remove();
                }
            }
            course.setModules(temp.getModules());
            course.setStudents(temp.getStudents());
//            course.setCreator(temp.getCreator());
            temp = courseRepository.save(course);
            createModules(forSave, temp);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if(courseRepository.findById(id).isEmpty()){
            return false;
        }else{
            courseRepository.deleteById(id);
            return true;
        }

    }

    public void createModules(List<Module> modules, Course course) {
        for (Module m : modules) {
            List<Lesson> forSave = m.getLessons();
            Module temp = moduleRepository.findById(m.getId() != null ? m.getId() : -1).orElse(null);
            if (temp == null) {
                temp = new Module(m.getPosition(), m.getTitle(), m.getDescription(), m.getDate(), course);
                temp = moduleRepository.save(temp);
                createLessons(forSave, temp);
            } else {
                Iterator<Lesson> itr = temp.getLessons().iterator();
                while (itr.hasNext()) {
                    Lesson x = itr.next();
                    Iterator<Lesson> newValues = forSave.iterator();
                    boolean flag = false;
                    while (newValues.hasNext()) {
                        Lesson val = newValues.next();
                        if (Objects.equals(val.getId(), x.getId())) {
                            flag = true;
                            break;
                        }

                    }
                    if (!flag) {
                        lessonRepository.deleteById(x.getId());
                        itr.remove();
                    }
                }
                m.setLessons(temp.getLessons());
                m.setCourse(course);
                temp = moduleRepository.save(m);
                createLessons(forSave, temp);
            }

        }
    }

    public void createLessons(List<Lesson> lessons, Module module) {
        if (lessons != null) {
            for (Lesson l : lessons) {
                Lesson lesson = lessonRepository.findById(l.getId() != null ? l.getId() : -1).orElse(null);
                if (lesson == null) {
                    Lesson temp = new Lesson(l.getPosition(), l.getTitle(), l.getTeacher(), l.getDate(), module);
                    lessonRepository.save(temp);
                } else {
                    l.setModule(module);
                    l.setSteps(lesson.getSteps());
                    lessonRepository.save(l);
                }
            }
        }
    }
}
