package net.wogus.jhblog.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wogus.jhblog.app.base.Rq;
import net.wogus.jhblog.app.dto.PostDto;
import net.wogus.jhblog.app.service.PostService;
import net.wogus.jhblog.app.form.PostForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/write")
    public String showWrite() {
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@Valid PostForm postForm, @RequestParam("imageFile") List<MultipartFile> imageFiles) throws IOException {
        long postId = postService.write(postForm, imageFiles);
        return Rq.redirectWithMsg("/post/" + postId, "%d번 글이 생성되었습니다.".formatted(postId));
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<PostDto> _postDto = postService.getArticleById(id);
        if (_postDto.isPresent()) {
            model.addAttribute("post", _postDto.get());
            return "post/modify";
        }
        return "post/list";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, Model model, @Valid PostForm postForm, @RequestParam("imageFile") List<MultipartFile> imageFiles) throws IOException {
        Optional<PostDto> _postDto = postService.updatePost(id, postForm, imageFiles);
        if (_postDto.isPresent()) {
            model.addAttribute("post", _postDto.get());
            return "post/detail";
        }
        return "post/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<PostDto> _postDto = postService.getArticleById(id);
        if (_postDto.isPresent()) {
            model.addAttribute("post", _postDto.get());
            return "post/detail";
        }
        return "post/list";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        postService.updateIsDeletedById(id);
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<PostDto> postDtos = postService.getPosts();
        model.addAttribute("posts", postDtos);
        return "post/list";
    }
}
