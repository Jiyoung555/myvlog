package com.daum.myvlog.service;
import com.daum.myvlog.dto.ArticleForm;
import com.daum.myvlog.entity.Article;
import com.daum.myvlog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article create(ArticleForm form){
        log.info(form.toString());

        // dto -> entity 변경 -> db 저장
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);

        return saved;
    }

    @Transactional
    public Article update(Long id, ArticleForm form) {
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );

        target.rewrite(form.getTitle(), form.getContent());
        Article updated = articleRepository.save(target);
        log.info("updated: " + updated.toString());

        return updated;
    }

    @Transactional
    public Long destroy(Long id) {
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );

        articleRepository.delete(target);
        log.info("삭제 완료  target: " + target.toString());

        return target.getId();
    }
}
