package com.june.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public class StepDto {
    private Integer id;
    private String description;

    private Lesson lesson;
    private ArrayList<String> encodedFiles=new ArrayList<>();
}
