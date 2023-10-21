package com.june.web.repositories;

import com.june.web.models.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Integer> {
}