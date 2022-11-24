package net.wogus.jhblog.app.post.repository;

import net.wogus.jhblog.app.post.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
