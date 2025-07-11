package mybook_insight.io.mybook_insight;

import org.springframework.boot.SpringApplication;

public class TestMybookInsightApplication {

	public static void main(String[] args) {
		SpringApplication.from(MybookInsightApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
