package com.fon.is.oracleelasticsearchbenchmark;

import com.fon.is.oracleelasticsearchbenchmark.queries.NewOracleQueries;
import org.elasticsearch.xpack.sql.jdbc.EsDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${elasticsearch.user}")
	private String elasticUser;

	@Value("${elasticsearch.password}")
	private String elasticPassword;

	@Value("${elasticsearch.url}")
	private String elasticUrl;

	@Value("${spring.datasource.url}")
	private String oracleUrl;

	@Value("${spring.datasource.username}")
	private String oracleUser;

	@Value("${spring.datasource.password}")
	private String oraclePassword;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fetchDataOracle(NewOracleQueries.AFTER_B_TREE_INDEXING);
	}

	private void fetchDataOracle(String query) throws SQLException, ClassNotFoundException {
		System.out.println("===============ORACLE=================");
		Class.forName("oracle.jdbc.driver.OracleDriver");

//		Instant start = Instant.now();
		try (Connection con = DriverManager.getConnection(oracleUrl, oracleUser, oraclePassword);) {
			Statement statement = con.createStatement();
			Instant start = Instant.now();
			ResultSet results = statement.executeQuery(query);
			Instant end = Instant.now();
			System.out.println("Duration: " + Duration.between(start, end));
			int rowCount = 0;
			while (results.next()) {
				++rowCount;
			}
			System.out.println("result.size: " + rowCount);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void fetchDataElastic(String query) throws SQLException {
		System.out.println("===============ELASTIC=================");

		Properties connectionProperties = new Properties();
		connectionProperties.put("user", elasticUser);
		connectionProperties.put("password", elasticPassword);

		EsDataSource dataSource = new EsDataSource();
		dataSource.setUrl(elasticUrl);
		dataSource.setProperties(connectionProperties);
		Instant start = Instant.now();
		Connection connection = dataSource.getConnection();
		try (Statement statement = connection.createStatement()) {
//			ResultSet results = statement.executeQuery("SELECT * FROM company WHERE r_id = 2");
			ResultSet results = statement.executeQuery(query);
			Instant end = Instant.now();
			System.out.println("Duration: " + Duration.between(start, end));
			int rowCount = 0;
			while (results.next()) {
				++rowCount;
			}
			System.out.println("result.size: " + rowCount);


//			List<Long> ids = new ArrayList();
//			while (results.next()) {
//				++rowCount;
//				ids.add(results.getLong("company_id"));
//				System.out.println("Id " + rowCount + ": " + results.getLong("company_id"));
//			}
//			System.out.println("result.size: " + rowCount);
//			ids.forEach(System.out::println);
		}
	}
}
