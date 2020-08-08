package com.daum.myvlog.repository;
import com.daum.myvlog.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
