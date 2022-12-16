package net.wogus.jhblog.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachmentDto {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String orgFileName;
    private String storeFileName;
    private long postId;
    private boolean isDeleted;

    @Builder
    public AttachmentDto(String orgFileName, String storePath) {
        this.orgFileName = orgFileName;
        this.storeFileName = storePath;
    }
}
