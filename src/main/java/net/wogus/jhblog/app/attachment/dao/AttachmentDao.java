package net.wogus.jhblog.app.attachment.dao;

import net.wogus.jhblog.app.attachment.dto.Attachment;
import net.wogus.jhblog.app.post.dto.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttachmentDao {

    @Select("""
            <script>
            SELECT *
            FROM attachment
            </script>
            """)
    public List<Attachment> findAll();

    @Insert("""
            <script>
            INSERT INTO attachment
            SET attachmentCreateDate = NOW(),
            attachmentModifyDate = NOW(),
            attachmentOrgFileName = #{attachment.attachmentOrgFileName},
            attachmentStoreFileName = #{attachment.attachmentStoreFileName},
            postId = #{attachment.postId}
            </script>
            """)
    public void save(@Param("attachment") Attachment attachment);

    @Select("""
            <script>
            SELECT *
            FROM attachment
            WHERE postId = #{postId}
            </script>
            """)
    public Attachment findByPostId(@Param("postId") long postId);
}
