package mybook_insight.io.mybook_insight;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MybookInsightApplicationTests {

	@Test
	void contextLoads() {
	}

}
