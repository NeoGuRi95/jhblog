package net.wogus.jhblog.app.form;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
public class PostForm {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;

    @Builder
    public PostForm(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
