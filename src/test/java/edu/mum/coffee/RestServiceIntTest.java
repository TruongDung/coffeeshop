package edu.mum.coffee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.coffee.domain.Product;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoffeShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceIntTest {
	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper objectMapper;

	private RestTemplate restTemplate = new BasicAuthRestTemplate("admin@mum.edu", "123456");

	private HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private List<Product> getAllProduct() throws IOException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/product-list"),
				HttpMethod.GET, entity, String.class);
		List<Product> products = objectMapper.readValue(response.getBody(), new TypeReference<List<Product>>() {
		});
		return products;
	}

	@Test
	public void testGetAllProducts() throws JSONException, IOException {
		List<Product> products = getAllProduct();
		Assert.assertNotNull(products);
		Assert.assertEquals(4, products.size());
	}

	@Test
	public void testDeleteProduct() throws IOException {
		List<Product> products = getAllProduct();
		Assert.assertEquals(4, products.size());

		HttpEntity<Void> entity = new HttpEntity<Void>(null, headers);
		restTemplate.exchange(
				createURLWithPort("/product/-1"),
				HttpMethod.DELETE, entity, Void.class);

		products = getAllProduct();
		Assert.assertEquals(3, products.size());
	}
}
