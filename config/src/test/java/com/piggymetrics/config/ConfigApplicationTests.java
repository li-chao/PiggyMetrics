
package com.piggymetrics.config;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"CONFIG_SERVICE_PASSWORD = pwd"})
public class ConfigApplicationTests {
    
    
    @Autowired
    private TestRestTemplate restTemplate;
    

	@Test
	public void configurationAvailable() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> response = restTemplate.withBasicAuth("user", "pwd").getForEntity(
				"/app/cloud", Map.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void envPostAvailable() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> response = restTemplate.withBasicAuth("user", "pwd").postForEntity(
				"/env", form, Map.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
