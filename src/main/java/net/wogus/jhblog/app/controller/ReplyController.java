package net.wogus.jhblog.app.controller;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{id}")
    public String createReply(@PathVariable("id") long id, @RequestParam String content) {
        this.replyService.create(id, content);
        return String.format("redirect:/post/%s", id);
    }
}
