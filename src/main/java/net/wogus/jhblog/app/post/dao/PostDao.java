package net.wogus.jhblog.app.post.dao;

import net.wogus.jhblog.app.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDao {
    @Select("""
            <script>
            SELECT *
            FROM post
            WHERE isDeleted IS NULL
            </script>
            """)
    List<Post> getPostList();

    @Insert("""
            <script>
            INSERT INTO post
            SET postCreateDate = NOW(),
            postModifyDate = NOW(),
            postSubject = #{subject},
            postContent = #{content},
            postContentHtml = #{contentHtml}
            </script>
            """)
    void write(@Param("subject") String subject, @Param("content") String content, @Param("contentHtml") String contentHtml);

    @Select("""
            SELECT LAST_INSERT_ID()
            """)
    long getLastInsertId();

    @Select("""
            <script>
            SELECT *
            FROM post
            WHERE postId = #{id}
            </script>
            """)
    Post getPostById(@Param("id") long id);

    @Delete("""
            <script>
            UPDATE post
            SET isDeleted = 1
            WHERE postId = #{id}
            </script>
            """)
    void updateIsDeletedById(@Param("id") long id);
}
