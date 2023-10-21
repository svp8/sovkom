package com.june.web.repositories;

import com.june.web.models.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachedFileRepository extends JpaRepository<AttachedFile, Integer> {
}