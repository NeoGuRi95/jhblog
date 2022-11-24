package net.wogus.jhblog.app.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private long id;
    private LocalDateTime postCreateDate;
    private LocalDateTime postModifyDate;
    private String postSubject;
    private String postContent;
    private String postContentHtml;
    private String postImageStoreFileName;
    private boolean isDeleted;
}
