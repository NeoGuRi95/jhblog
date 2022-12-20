package net.wogus.jhblog.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.wogus.jhblog.app.entity.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String subject;
    private String content;
    private Set<String> imageStoreFileName;
    private Set<ReplyDto> replys;
    private boolean isDeleted;

    public Page<PostDto> toDtoPage(Page<Post> page) {
        return page.map(post ->
                PostDto.builder()
                        .id(post.getId())
                        .createDate(post.getCreateDate())
                        .modifyDate(post.getModifyDate())
                        .subject(post.getPostSubject())
                        .content(post.getPostContent())
                        .imageStoreFileName(post.getImagePathSet())
                        .replys(post.getReplyDtoSet())
                        .isDeleted(post.isDeleted())
                        .build()
        );
    }
}
