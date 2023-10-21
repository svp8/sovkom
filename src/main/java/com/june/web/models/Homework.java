package com.june.web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "homework")
public class Homework extends Step {
    private LocalDateTime deadline;
    @OneToMany(mappedBy = "homework",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<HomeworkFile> answers=new ArrayList<>();
}