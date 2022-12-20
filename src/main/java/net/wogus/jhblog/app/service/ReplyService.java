package net.wogus.jhblog.app.service;

import lombok.RequiredArgsConstructor;
import net.wogus.jhblog.app.entity.Post;
import net.wogus.jhblog.app.entity.Reply;
import net.wogus.jhblog.app.repository.PostRepository;
import net.wogus.jhblog.app.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public void create(long id, String content) {
        Optional<Post> _post = postRepository.findById(id);
        if (_post.isPresent()) {
            Reply reply = new Reply();
            reply.setContent(content);
            reply.setPost(_post.get());
            replyRepository.save(reply);
        }
    }
}
