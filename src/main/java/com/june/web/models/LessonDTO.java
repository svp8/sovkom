package com.june.web.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class LessonDTO {
        private Integer id;
        private int position;
        private String title;
        private String teacher;

        public LessonDTO(int position, String title, String teacher, String date, Module module) {
            this.position = position;
            this.title = title;
            this.teacher = teacher;
            this.date = date;
            this.module = module;
        }

        private String date;
        private Module module;
        private List<StepDto> steps=new ArrayList<>();
}
