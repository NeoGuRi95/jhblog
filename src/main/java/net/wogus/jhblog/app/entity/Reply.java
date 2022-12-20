package net.wogus.jhblog.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.wogus.jhblog.app.base.BaseEntity;
import net.wogus.jhblog.app.dto.ReplyDto;

import javax.persistence.Column;
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
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public ReplyDto toDto() {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setId(this.id);
        replyDto.setContent(this.content);
        replyDto.setCreateDate(this.getCreateDate());
        replyDto.setModifyDate(this.getModifyDate());
        return replyDto;
    }
}
