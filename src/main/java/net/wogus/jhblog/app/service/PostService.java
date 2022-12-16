package net.wogus.jhblog.app.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.form.PostForm;
import net.wogus.jhblog.app.util.FileStore;
import net.wogus.jhblog.app.dto.AttachmentDto;
import net.wogus.jhblog.app.entity.Attachment;
import net.wogus.jhblog.app.repository.AttachmentRepository;
import net.wogus.jhblog.app.dto.PostDto;
import net.wogus.jhblog.app.entity.Post;
import net.wogus.jhblog.app.repository.PostRepository;
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
            postDtoList.add(postDto);
        }

        return postDtoList;
    }

    /*게시글 저장*/
    @Transactional
    public long write(PostForm postForm, List<MultipartFile> imageFiles) throws IOException {
        Post post = new Post(postForm.getSubject(), postForm.getContent());
        /*이미지 저장*/
        List<AttachmentDto> attachmentDtos = fileStore.storeFiles(imageFiles);
        for (AttachmentDto attachmentDto : attachmentDtos) {
            Attachment attachment = new Attachment(attachmentDto);
            post.addAttachment(attachment);
        }

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
    public Optional<PostDto> updatePost(Long id, PostForm postForm, List<MultipartFile> imageFiles) throws IOException {
        Optional<Post> _post = postRepository.findById(id);
        PostDto postDto = null;
        if (_post.isPresent()) {
            Post post = _post.get();
            post.setPostSubject(postForm.getSubject());
            post.setPostContent(postForm.getContent());
            /*이미지 저장*/
            List<AttachmentDto> _imageFiles = fileStore.storeFiles(imageFiles);
            for (AttachmentDto attachmentDto : _imageFiles) {
                Attachment attachment = new Attachment(attachmentDto);
                post.addAttachment(attachment);
            }
            postDto = post.toDto();
        }

        return Optional.ofNullable(postDto);
    }
}
