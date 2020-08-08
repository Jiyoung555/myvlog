package com.daum.myvlog.entity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", article=" + article +
                '}';
    }

    public void stickTo(Article article) {
        this.article = article;
    }

    public void rewrite(String content) {
        this.content = content;
    }
}
