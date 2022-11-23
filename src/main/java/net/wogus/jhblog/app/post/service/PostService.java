package net.wogus.jhblog.app.post.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.attachment.dao.AttachmentDao;
import net.wogus.jhblog.app.attachment.dto.Attachment;
import net.wogus.jhblog.app.post.dao.PostDao;
import net.wogus.jhblog.app.post.dto.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;
    private final AttachmentDao attachmentDao;

    public List<Post> getArticles() {
        List<Post> postList = postDao.getPostList();
        for (Post post : postList) {
            Attachment attachment = attachmentDao.findByPostId(post.getPostId());
            if (attachment != null) post.setPostImageStoreFileName(attachment.getAttachmentStoreFileName());
        }
        return postList;
    }

    public long write(String subject, String content, String contentHtml) {
        postDao.write(subject, content, contentHtml);
        return postDao.getLastInsertId();
    }

    public Post getArticleById(long id) {
        return postDao.getPostById(id);
    }

    public void updateIsDeletedById(long id) {
        postDao.updateIsDeletedById(id);
    }
}
