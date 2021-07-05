package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
	@Test
	void contextLoads() {
		Boolean result = jdbcTemplate.queryForObject("select '{}'::jsonb ?? ?", Boolean.class, "Hello");
		Assertions.assertEquals(false, result);

		result = jdbcTemplate.queryForObject("select '{\"Hello\": \"World\"}'::jsonb ?? ?", Boolean.class, "Hello");
		Assertions.assertEquals(true, result);

		result = namedParameterJdbcTemplate.queryForObject("select '{}'::jsonb ?? :key", Map.of("key", "Hello"), Boolean.class);
		Assertions.assertEquals(false, result);

		result = namedParameterJdbcTemplate.queryForObject("select '{\"Hello\": \"World\"}'::jsonb ?? :key", Map.of("key", "Hello"), Boolean.class);
		Assertions.assertEquals(true, result);

	}
}
