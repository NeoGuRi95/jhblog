package net.wogus.jhblog.app.repository;

import net.wogus.jhblog.app.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
