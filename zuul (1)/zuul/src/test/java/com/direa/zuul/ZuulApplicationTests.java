package com.direa.zuul;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class ZuulApplicationTests {

	@Test
	void contextLoads() {
	}

	@LocalServerPort
	private int port;
	private static final String SIMPLE_GREETING = "/service-a/simple";
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void whenClientCallApi_thenLogAndReturnResponseBody() {
		String url = "http://localhost:" + port + SIMPLE_GREETING;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertEquals(response.getBody(), "Hi!!!!");
	}


}
