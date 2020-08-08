package com.daum.myvlog.dto;
import com.daum.myvlog.entity.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentForm {
    private Long id;
    private String author;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .author(author)
                .content(content)
                .build();
    }
}
