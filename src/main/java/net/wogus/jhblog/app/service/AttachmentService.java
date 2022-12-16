package net.wogus.jhblog.app.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.util.FileStore;
import net.wogus.jhblog.app.repository.AttachmentRepository;
import net.wogus.jhblog.app.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final PostRepository postRepository;
    private final FileStore fileStore;

}
