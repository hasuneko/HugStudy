package com.example.demoSpring.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoSpring.dto.StudyRecordRequest;
import com.example.demoSpring.dto.SubjectRequest;
import com.example.demoSpring.entity.StudyRecordEntity;
import com.example.demoSpring.entity.SubjectEntity;
import com.example.demoSpring.repository.StudyRecordRepository;
import com.example.demoSpring.repository.SubjectRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectEntity> findAll() {
        return subjectRepository.findAll();
    }

    public void save(SubjectRequest request) {
        SubjectEntity entity = new SubjectEntity();
        entity.setTitle(request.getTitle());
        entity.setTotalPages(request.getTotalPages());
        subjectRepository.save(entity);
    }
    @Autowired
    private StudyRecordRepository studyRecordRepository;

    /**
     * 学習記録を保存する
     */
    public void saveRecord(Integer subjectId, StudyRecordRequest request) {
        // 1. どの科目に対する記録か、親を取得
        SubjectEntity subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("科目が見つかりません"));

        // 2. Entityに詰め替える
        StudyRecordEntity entity = new StudyRecordEntity();
        entity.setSubject(subject); // 親と子の紐付け
        entity.setSection(request.getSection());
        entity.setStartPage(request.getStartPage());
        entity.setEndPage(request.getEndPage());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setMemo(request.getMemo());

        // 3. 勉強時間の自動計算
        if (request.getStartTime() != null && request.getEndTime() != null) {
            long minutes = Duration.between(request.getStartTime(), request.getEndTime()).toMinutes();
            entity.setDuration((int) minutes);
        }

        // 4. 保存
        studyRecordRepository.save(entity);
    }
    /**
     * 編集するために1件の記録を取得する
     */
    public StudyRecordEntity getRecordById(Integer recordId) {
        return studyRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("記録が見つかりません"));
    }

    /**
     * 記録を更新する
     */
    public void updateRecord(Integer recordId, StudyRecordRequest request) {
        StudyRecordEntity entity = getRecordById(recordId);
        
        // 内容を上書き
        entity.setSection(request.getSection());
        entity.setStartPage(request.getStartPage());
        entity.setEndPage(request.getEndPage());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setMemo(request.getMemo());
        
        // 時間の再計算
        if (request.getStartTime() != null && request.getEndTime() != null) {
            long minutes = java.time.Duration.between(request.getStartTime(), request.getEndTime()).toMinutes();
            entity.setDuration((int) minutes);
        }
        
        studyRecordRepository.save(entity);
    }
    /**
     * 学習記録を削除する
     */
    public void deleteRecord(Integer recordId) {
        // 指定されたID（recordId）の学習記録を削除する
        studyRecordRepository.deleteById(recordId);
    }
    /**
     * 科目そのものを削除する
     */
    public void deleteSubject(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}