package net.wogus.jhblog.app.attachment.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.attachment.FileStore;
import net.wogus.jhblog.app.attachment.repository.AttachmentRepository;
import net.wogus.jhblog.app.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final PostRepository postRepository;
    private final FileStore fileStore;

}
