package com.daum.myvlog.service;
import com.daum.myvlog.dto.CommentForm;
import com.daum.myvlog.entity.Article;
import com.daum.myvlog.entity.Comment;
import com.daum.myvlog.repository.ArticleRepository;
import com.daum.myvlog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Comment create(Long articleId, CommentForm form) {
        log.info(form.toString());

        // dto -> entity로 변경
        Comment comment = form.toEntity();

        // 댓글을 -> 게시글에 붙이기
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );
        comment.stickTo(article);

        // entity -> db에 저장
        Comment saved = commentRepository.save(comment);
        return saved;
    }

    @Transactional
    public Comment update(Long id, CommentForm form) {
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글 없음")
        );

        target.rewrite(form.getContent());
        Comment updated = commentRepository.save(target);
        log.info("updated: " + updated.toString());

        return updated;
    }
}
