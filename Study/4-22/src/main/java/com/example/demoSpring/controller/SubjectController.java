package com.example.demoSpring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpring.dto.StudyRecordRequest;
import com.example.demoSpring.dto.SubjectRequest;
import com.example.demoSpring.entity.StudyRecordEntity;
import com.example.demoSpring.entity.SubjectEntity;
import com.example.demoSpring.repository.SubjectRepository;
import com.example.demoSpring.service.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectRepository subjectRepository;

    // 科目登録画面を表示
    @GetMapping("/add")
    public String displayAdd(Model model) {
        model.addAttribute("subjectRequest", new SubjectRequest());
        return "subject/add";
    }

    // 科目を保存
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute SubjectRequest subjectRequest, 
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "subject/add";
        }
        subjectService.save(subjectRequest);
        return "redirect:/subject/list"; 
    }
    
    // 一覧画面
    @GetMapping("/list")
    public String displayList(Model model) {
        model.addAttribute("subjectList", subjectService.findAll());
        return "subject/list";
    }

    // 学習記録入力画面を表示
    @GetMapping("/record/{subjectId}")
    public String displayRecordForm(@PathVariable("subjectId") Integer subjectId, Model model) {
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("recordRequest", new StudyRecordRequest());
        return "subject/record";
    }

    // 学習記録を保存
    @PostMapping("/record/{subjectId}/save")
    public String saveRecord(@PathVariable("subjectId") Integer subjectId,
                             @ModelAttribute StudyRecordRequest recordRequest) {
        subjectService.saveRecord(subjectId, recordRequest);
        return "redirect:/subject/list";
    }

    // 特定の科目の学習履歴を表示
    @GetMapping("/history/{subjectId}")
    public String displayHistory(@PathVariable("subjectId") Integer subjectId, Model model) {
        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("科目が見つかりません"));
        model.addAttribute("subject", subject);        
        return "subject/history";
    }

    // 編集画面を表示
    @GetMapping("/record/edit/{recordId}")
    public String showEditRecord(@PathVariable("recordId") Integer recordId, Model model) {
        StudyRecordEntity record = subjectService.getRecordById(recordId);
        model.addAttribute("studyRecordRequest", record);
        model.addAttribute("subjectId", record.getSubject().getId());
        model.addAttribute("recordId", recordId);
        return "subject/record_edit"; 
    }

    // 更新を実行
    @PostMapping("/record/edit/{recordId}")
    public String updateRecord(@PathVariable("recordId") Integer recordId, 
                               @ModelAttribute StudyRecordRequest recordRequest,
                               @RequestParam("subjectId") Integer subjectId) {
        subjectService.updateRecord(recordId, recordRequest);
        return "redirect:/subject/history/" + subjectId;
    }

    // 学習記録を削除
    @PostMapping("/record/delete/{recordId}")
    public String deleteRecord(@PathVariable("recordId") Integer recordId, 
                               @RequestParam("subjectId") Integer subjectId) {
        subjectService.deleteRecord(recordId);
        return "redirect:/subject/history/" + subjectId;
    }

    /**
     * 科目そのものを削除する
     */
    @PostMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id) {
        subjectService.deleteSubject(id);
        return "redirect:/subject/list";
    }
}