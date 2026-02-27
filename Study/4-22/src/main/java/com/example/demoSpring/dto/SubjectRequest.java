package com.example.demoSpring.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubjectRequest {
    @NotBlank(message = "科目名を入力してください")
    private String title;

    @NotNull(message = "全体のボリュームを入力してください")
    @Min(value = 1, message = "1以上で入力してください")
    private Integer totalPages;
}