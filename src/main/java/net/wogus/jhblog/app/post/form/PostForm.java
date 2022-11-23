package net.wogus.jhblog.app.post.form;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class PostForm {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    @NotBlank
    private String contentHtml;
    private MultipartFile imageFile;

    @Builder
    public PostForm(String subject, String content, String contentHtml, MultipartFile imageFile) {
        this.subject = subject;
        this.content = content;
        this.contentHtml = contentHtml;
        this.imageFile = imageFile;
    }

}
