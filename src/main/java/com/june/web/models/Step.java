package com.june.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "step")
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Lesson lesson;
    @OneToMany(mappedBy = "step",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<AttachedFile> files=new ArrayList<>();
}