package net.wogus.jhblog.app.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.entity.Attachment;
import net.wogus.jhblog.app.util.FileStore;
import net.wogus.jhblog.app.repository.AttachmentRepository;
import net.wogus.jhblog.app.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final PostRepository postRepository;
    private final FileStore fileStore;

    /*게시글 사진 삭제(isDeleted 컬럼 수정)*/
    @Transactional
    public void updateIsDeletedById(Long id) {
        Optional<Attachment> _attachment = attachmentRepository.findById(id);
        _attachment.ifPresent(attachment -> attachment.setDeleted(true));
    }
}
