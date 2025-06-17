package mybook_insight.io.mybook_insight.infrastructures;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {
	private static final String DATABASE_NAME="testdb";
	private static final String DATABASE_USERNAME="test";
	private static final String DATABASE_PASSWORD="test";


	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
			.withDatabaseName(DATABASE_NAME)
			.withUsername(DATABASE_USERNAME)
			.withPassword(DATABASE_PASSWORD);
	}

}
