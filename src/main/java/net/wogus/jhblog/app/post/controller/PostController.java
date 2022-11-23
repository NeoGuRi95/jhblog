package net.wogus.jhblog.app.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wogus.jhblog.app.attachment.service.AttachmentService;
import net.wogus.jhblog.app.base.Rq;
import net.wogus.jhblog.app.post.dto.Post;
import net.wogus.jhblog.app.post.service.PostService;
import net.wogus.jhblog.app.post.form.PostForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {

    private final PostService postService;
    private final AttachmentService attachmentService;

    @GetMapping("/write")
    public String showWrite() {
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@Valid PostForm postForm) throws IOException {
        long postId = postService.write(postForm.getSubject(), postForm.getContent(), postForm.getContentHtml());
        attachmentService.saveAttachment(postForm.getImageFile(), postId);
        return Rq.redirectWithMsg("/post/" + postId, "%d번 글이 생성되었습니다.".formatted(postId));
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.getArticleById(id);
        model.addAttribute("post", post);
        return "post/detail";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Post> posts = postService.getArticles();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        postService.updateIsDeletedById(id);
        return "redirect:/post/list";
    }
}
