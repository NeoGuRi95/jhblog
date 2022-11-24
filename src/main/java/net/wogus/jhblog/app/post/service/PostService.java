package net.wogus.jhblog.app.post.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.attachment.FileStore;
import net.wogus.jhblog.app.attachment.dto.AttachmentDto;
import net.wogus.jhblog.app.attachment.entity.Attachment;
import net.wogus.jhblog.app.attachment.repository.AttachmentRepository;
import net.wogus.jhblog.app.post.dto.PostDto;
import net.wogus.jhblog.app.post.entity.Post;
import net.wogus.jhblog.app.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileStore fileStore;

    public List<PostDto> getPosts() {
        List<PostDto> postDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();

        for (Post post : postList) {
            PostDto postDto = post.toDto();
            // 대표 이미지 찾기
            Optional<List<Attachment>> _attachmentList = attachmentRepository.findAllByPostId(postDto.getId());
            if (_attachmentList.isPresent()) {
                List<Attachment> attachmentList = _attachmentList.get();
                Attachment attachment = attachmentList.get(0);
                postDto.setPostImageStoreFileName(attachment.getStoreFileName());
            }
            postDtoList.add(postDto);
        }

        return postDtoList;
    }

    @Transactional
    public long write(String subject, String content, String contentHtml, MultipartFile multipartFile) throws IOException {
        /*게시글 저장*/
        Post post = new Post(subject, content, contentHtml);

        /*썸네일 이미지 저장*/
        AttachmentDto imageFile = fileStore.storeFile(multipartFile);
        Attachment attachment = new Attachment(imageFile);
        post.addAttachment(attachment);

        return postRepository.save(post).getId();
    }

    public Optional<PostDto> getArticleById(long id) {
        Optional<Post> _post = postRepository.findById(id);
        PostDto postDto = null;

        if (_post.isPresent()) {
            postDto = _post.get().toDto();
        }

        return Optional.ofNullable(postDto);
    }

    @Transactional
    public void updateIsDeletedById(long id) {
        Optional<Post> _post = postRepository.findById(id);
        _post.ifPresent(post -> post.setDeleted(true));
    }
}
