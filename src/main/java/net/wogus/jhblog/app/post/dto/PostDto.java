package net.wogus.jhblog.app.post.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String subject;
    private String content;
    private Set<String> imageStoreFileName;
    private boolean isDeleted;
}
