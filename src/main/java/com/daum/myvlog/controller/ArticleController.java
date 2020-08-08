package com.daum.myvlog.controller;
import com.daum.myvlog.entity.Article;
import com.daum.myvlog.entity.Comment;
import com.daum.myvlog.repository.ArticleRepository;
import com.daum.myvlog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/articles")
    public String index(Model model){
        model.addAttribute("msg", "안녕하세요");

        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    // Article 상세보기
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );
        model.addAttribute("article", article);

        // 댓글들을 화면에 보여줄 거임 (댓글 목록)
        model.addAttribute("comments", article.getComments());

        return "articles/show";
    }

    // Article 수정 화면을 보여줘
    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );

        model.addAttribute("article", article);
        return "articles/edit";
    }





}
