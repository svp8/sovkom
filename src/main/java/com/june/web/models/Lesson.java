package com.june.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lesson")
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int position;
    private String title;
    private String teacher;

    public Lesson(int position, String title, String teacher, String date, Module module) {
        this.position = position;
        this.title = title;
        this.teacher = teacher;
        this.date = date;
        this.module = module;
    }

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Module module;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private List<Step> steps=new ArrayList<>();
}