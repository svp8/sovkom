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
@Table(name = "course")
@NoArgsConstructor
public class Course {
    public Course(User creator, String title, String description) {
        this.creator = creator;
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    private User creator;
    private String title;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "course_user",
            inverseJoinColumns = @JoinColumn(name = "student_id"),
            joinColumns = @JoinColumn(name = "course_id"))
    private List<User> students=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<Module> modules=new ArrayList<>();
}