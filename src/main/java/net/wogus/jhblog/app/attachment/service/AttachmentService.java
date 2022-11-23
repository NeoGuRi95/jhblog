package net.wogus.jhblog.app.attachment.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.attachment.FileStore;
import net.wogus.jhblog.app.attachment.dao.AttachmentDao;
import net.wogus.jhblog.app.attachment.dto.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentDao attachmentDao;
    private final FileStore fileStore;

    public Attachment saveAttachment(MultipartFile multipartFile, long postId) throws IOException {
        Attachment imageFile = fileStore.storeFile(multipartFile);
        imageFile.setPostId(postId);
        attachmentDao.save(imageFile);

        return imageFile;
    }
}
