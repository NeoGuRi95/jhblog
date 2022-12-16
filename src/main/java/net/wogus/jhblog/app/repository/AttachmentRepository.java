package net.wogus.jhblog.app.repository;

import net.wogus.jhblog.app.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<List<Attachment>> findAllByPostId(Long postId);
}
