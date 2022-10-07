package com.ll.exam.app__2022_10_05.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.app__2022_10_05.base.entity.BaseEntity;
import com.ll.exam.app__2022_10_05.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {

    @ManyToOne
    private Member author;

    private String subject;

    @JsonIgnore
    private String content;

    public Article(long id) {
        super(id);
    }

}
