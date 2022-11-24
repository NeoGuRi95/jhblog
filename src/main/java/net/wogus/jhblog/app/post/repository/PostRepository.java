package net.wogus.jhblog.app.post.repository;

import net.wogus.jhblog.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
