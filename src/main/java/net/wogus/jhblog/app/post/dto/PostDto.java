package net.wogus.jhblog.app.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String subject;
    private String content;
    private String imageStoreFileName;
    private boolean isDeleted;
}
