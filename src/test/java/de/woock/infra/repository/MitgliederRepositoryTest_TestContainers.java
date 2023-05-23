package de.woock.infra.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public class MitgliederRepositoryTest_TestContainers {
	private static final DockerImageName MARIADB_IMAGE = DockerImageName.parse("mariadb:10.5.5");

    @SuppressWarnings("rawtypes")
	@Container
    private static final MariaDBContainer mariaDB = new MariaDBContainer<>(MARIADB_IMAGE)
            .withDatabaseName("k8s_verein")
            .withUsername("root")
            .withPassword("admin");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url"     , mariaDB::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDB::getUsername);
        registry.add("spring.datasource.password", mariaDB::getPassword);
    }
    @Test
    void test() {
        assertTrue(mariaDB.isRunning());
    }

}
