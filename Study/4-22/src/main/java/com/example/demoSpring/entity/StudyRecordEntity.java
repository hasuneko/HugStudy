package com.example.demoSpring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "study_records")
@Data
public class StudyRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 多対1：どの科目の記録か
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    private String section;      // 学習した章
    private Integer startPage;   // 開始ページ
    private Integer endPage;     // 終了ページ
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;    // 勉強時間（分）
    
    @Column(columnDefinition = "TEXT")
    private String memo;
}