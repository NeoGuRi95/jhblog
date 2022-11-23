package net.wogus.jhblog.app.attachment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Attachment {
    private long attachmentId;
    private LocalDateTime attachmentCreateDate;
    private LocalDateTime attachmentModifyDate;
    private String attachmentOrgFileName;
    private String attachmentStoreFileName;
    private long postId;

    @Builder
    public Attachment(String orgFileName, String storePath) {
        this.attachmentOrgFileName = orgFileName;
        this.attachmentStoreFileName = storePath;
    }
}
