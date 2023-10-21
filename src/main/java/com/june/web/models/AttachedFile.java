package com.june.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AttachedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private String type;
    @Column(columnDefinition = "TEXT")
    private String encodedFile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Step step;
}
