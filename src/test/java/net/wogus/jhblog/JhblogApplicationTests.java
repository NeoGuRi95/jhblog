package net.wogus.jhblog;

import net.wogus.jhblog.app.post.dto.Post;
import net.wogus.jhblog.app.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JhblogApplicationTests {

	@Autowired
	private PostService postService;

	@Test
	@DisplayName("게시물 리스트 조회")
	void t1() {
		List<Post> postList = postService.getArticles();
		assertThat(postList.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("게시물 단건 조회")
	void t3() {
		Post post = postService.getArticleById(2L);
		assertThat(post).isNotNull();
	}

	@Test
	@DisplayName("게시물 작성")
	void t2() {
		long id = postService.write("제목4", "내용4", "내용4");
		assertThat(id).isGreaterThan(0);
	}
}
