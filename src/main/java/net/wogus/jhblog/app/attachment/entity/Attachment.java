package net.wogus.jhblog.app.attachment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.wogus.jhblog.app.attachment.dto.AttachmentDto;
import net.wogus.jhblog.app.base.BaseEntity;
import net.wogus.jhblog.app.post.entity.Post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Attachment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String orgFileName;

    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Attachment(AttachmentDto attachmentDto) {
        this.orgFileName = attachmentDto.getOrgFileName();
        this.storeFileName = attachmentDto.getStoreFileName();
    }
}
