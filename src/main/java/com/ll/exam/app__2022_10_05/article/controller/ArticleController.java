package com.ll.exam.app__2022_10_05.article.controller;

import com.ll.exam.app__2022_10_05.article.entity.Article;
import com.ll.exam.app__2022_10_05.article.service.ArticleService;
import com.ll.exam.app__2022_10_05.base.dto.RsData;
import com.ll.exam.app__2022_10_05.util.Util;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<RsData> list() {
        List<Article> articles = articleService.findAll();

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "articles", articles
                        )
                )
        );

    }

}
