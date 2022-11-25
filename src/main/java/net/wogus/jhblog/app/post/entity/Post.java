package net.wogus.jhblog.app.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.wogus.jhblog.app.attachment.entity.Attachment;
import net.wogus.jhblog.app.base.BaseEntity;
import net.wogus.jhblog.app.post.dto.PostDto;

import javax.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 200)
    private String postSubject;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    private boolean isDeleted;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Reply> replys;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Attachment> attachments;

    public Post(String subject, String content) {
        this.postSubject = subject;
        this.postContent = content;
        this.isDeleted = false;
        this.replys = new LinkedHashSet<>();
        this.attachments = new LinkedHashSet<>();
    }

    public PostDto toDto() {
        PostDto postDto = new PostDto();
        postDto.setCreateDate(this.getCreateDate());
        postDto.setModifyDate(this.getModifyDate());
        postDto.setId(this.id);
        postDto.setSubject(this.postSubject);
        postDto.setContent(this.postContent);
        postDto.setDeleted(this.isDeleted);
        return postDto;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setPost(this);
    }
}
