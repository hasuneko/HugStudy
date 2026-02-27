package com.example.demoSpring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "subjects")
@Data
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;      // 科目名（数学、英語など）
    private Integer totalPages; // 全体のボリューム（総ページ数）

    /**
     * 1対多：一つの科目に、日々の学習記録が複数紐づく
     * cascade = CascadeType.ALL      -> 科目を保存・削除した時に履歴も連動させる
     * orphanRemoval = true           -> 科目が消えたら、持ち主のいない履歴データも削除する
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyRecordEntity> records;

    /**
     * この科目の合計学習時間（分）を計算する
     */
    public Integer getTotalDuration() {
        if (records == null || records.isEmpty()) return 0;
        return records.stream()
                      .mapToInt(r -> r.getDuration() != null ? r.getDuration() : 0)
                      .sum();
    }

    /**
     * 現在の進捗ページ（最後に記録した終了ページ）を取得する
     */
    public Integer getCurrentPage() {
        if (records == null || records.isEmpty()) return 0;
        return records.stream()
                      .mapToInt(r -> r.getEndPage() != null ? r.getEndPage() : 0)
                      .max()
                      .orElse(0);
    }

    /**
     * 進捗率（％）を計算する
     */
    public int getProgressPercent() {
        if (totalPages == null || totalPages == 0) return 0;
        int percent = (getCurrentPage() * 100) / totalPages;
        return Math.min(percent, 100); // 100%を超えないようにする
    }
}