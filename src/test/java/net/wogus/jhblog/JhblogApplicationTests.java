package net.wogus.jhblog;

import net.wogus.jhblog.app.dto.PostDto;
import net.wogus.jhblog.app.form.PostForm;
import net.wogus.jhblog.app.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
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

	@Test
	@DisplayName("테스트 데이터 생성(300개)")
	@Rollback(false)
	void t3() throws IOException {
		for (int i = 1; i <= 300; i++) {
			PostForm postForm = new PostForm(String.format("테스트 데이터입니다:[%03d]", i), "내용무");
			postService.write(postForm, new ArrayList<>());
		}
	}
}
