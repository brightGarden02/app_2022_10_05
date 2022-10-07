package com.ll.exam.app__2022_10_05.article.repository;

import com.ll.exam.app__2022_10_05.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
