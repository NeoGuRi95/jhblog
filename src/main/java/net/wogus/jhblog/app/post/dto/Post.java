package net.wogus.jhblog.app.post.dto;

import lombok.Data;
import net.wogus.jhblog.app.attachment.dto.Attachment;

import java.time.LocalDateTime;

@Data
public class Post {
    private long postId;
    private LocalDateTime postCreateDate;
    private LocalDateTime postModifyDate;
    private String postSubject;
    private String postContent;
    private String postContentHtml;
    private String postImageStoreFileName;
}
