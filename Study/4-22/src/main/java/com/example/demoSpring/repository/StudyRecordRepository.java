package com.example.demoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoSpring.entity.StudyRecordEntity;

@Repository
public interface StudyRecordRepository extends JpaRepository<StudyRecordEntity, Integer> {
}