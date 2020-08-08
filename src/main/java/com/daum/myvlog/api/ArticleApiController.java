package com.daum.myvlog.api;
import com.daum.myvlog.dto.ArticleForm;
import com.daum.myvlog.entity.Article;
import com.daum.myvlog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    // Article 생성
    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form){
        Article saved = articleService.create(form);
        return saved.getId();
    }

    // Article 수정
    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        Article updated = articleService.update(id, form);
        return updated.getId();
    }

    // Article 삭제
    @DeleteMapping("/api/articles/{id}")
    public Long destroy(@PathVariable Long id){
        Long deletedId =  articleService.destroy(id);
        return deletedId;
    }

}
