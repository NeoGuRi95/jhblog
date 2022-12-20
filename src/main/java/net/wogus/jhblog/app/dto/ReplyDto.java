package net.wogus.jhblog.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String content;
}
