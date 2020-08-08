package com.daum.myvlog.api;
import com.daum.myvlog.dto.CommentForm;
import com.daum.myvlog.entity.Comment;
import com.daum.myvlog.repository.CommentRepository;
import com.daum.myvlog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/api/comments/{articleId}")
    public Long create(@PathVariable Long articleId, @RequestBody CommentForm form){
        Comment saved = commentService.create(articleId, form);
        return saved.getId();
    }

    // 댓글 수정
    @PutMapping("/api/comments/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentForm form){
        Comment updated = commentService.update(id, form);
        return updated.getId();
    }
}
