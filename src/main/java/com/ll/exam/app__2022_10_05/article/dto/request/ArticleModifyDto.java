package com.ll.exam.app__2022_10_05.article.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ArticleModifyDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String subject;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;


}
