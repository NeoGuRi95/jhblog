package net.wogus.jhblog;

import net.wogus.jhblog.app.post.dto.PostDto;
import net.wogus.jhblog.app.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JhblogApplicationTests {

	@Autowired
	private PostService postService;

	@Test
	@DisplayName("게시물 리스트 조회")
	void t1() {
		List<PostDto> postDtoList = postService.getPosts();
		assertThat(postDtoList.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("게시물 단건 조회")
	void t2() {
		Optional<PostDto> postDto = postService.getArticleById(1L);
		assertThat(postDto).isNotNull();
	}
}
