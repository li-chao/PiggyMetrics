
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConfigApplicationTests {
    
    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void configurationAvailable() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = restTemplate.getForEntity(
				"/app/cloud", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void envPostAvailable() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = restTemplate.postForEntity(
				"/admin/env", form, Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
