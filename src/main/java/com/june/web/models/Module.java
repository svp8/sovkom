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
@Table(name = "module")
@NoArgsConstructor
public class Module {
    public Module(int position, String title, String description, String date, Course course) {
        this.position = position;
        this.title = title;
        this.description = description;
        this.date = date;
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int position;
    private String title;
    private String description;
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Course course;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private List<Lesson> lessons=new ArrayList<>();
}