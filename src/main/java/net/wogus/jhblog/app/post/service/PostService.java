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

    /*전체 게시글 조회*/
    public List<PostDto> getPosts() {
        List<PostDto> postDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();

        for (Post post : postList) {
            if (post.isDeleted()) continue;
            PostDto postDto = post.toDto();
            // 대표 이미지 찾기
            Optional<List<Attachment>> _attachmentList = attachmentRepository.findAllByPostId(postDto.getId());
            if (_attachmentList.isPresent()) {
                List<Attachment> attachmentList = _attachmentList.get();
                Attachment attachment = attachmentList.get(0);
                postDto.setImageStoreFileName(attachment.getStoreFileName());
            }
            postDtoList.add(postDto);
        }

        return postDtoList;
    }

    /*게시글 저장*/
    @Transactional
    public long write(String subject, String content, MultipartFile multipartFile) throws IOException {
        Post post = new Post(subject, content);

        /*썸네일 이미지 저장*/
        AttachmentDto imageFile = fileStore.storeFile(multipartFile);
        Attachment attachment = new Attachment(imageFile);
        post.addAttachment(attachment);

        return postRepository.save(post).getId();
    }

    /*특정 게시글 조회*/
    public Optional<PostDto> getArticleById(Long id) {
        Optional<Post> _post = postRepository.findById(id);
        PostDto postDto = null;

        if (_post.isPresent()) {
            postDto = _post.get().toDto();
        }

        return Optional.ofNullable(postDto);
    }

    /*게시글 삭제(isDeleted 컬럼 수정)*/
    @Transactional
    public void updateIsDeletedById(Long id) {
        Optional<Post> _post = postRepository.findById(id);
        _post.ifPresent(post -> post.setDeleted(true));
    }

    /*게시글 수정(제목, 내용)*/
    @Transactional
    public void updatePost(Long id, String subject, String content) {
        Optional<Post> _post = postRepository.findById(id);
        if (_post.isPresent()) {
            Post post = _post.get();
            post.setPostSubject(subject);
            post.setPostContent(content);
        }
    }
}
