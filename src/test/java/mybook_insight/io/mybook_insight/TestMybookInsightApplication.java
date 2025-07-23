package mybook_insight.io.mybook_insight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import mybook_insight.io.mybook_insight.infrastructures.TestcontainersConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
public class TestMybookInsightApplication {

	public static void main(String[] args) {
		SpringApplication.from(MyBookInsightApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
