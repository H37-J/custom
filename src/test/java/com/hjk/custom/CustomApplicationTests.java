package com.hjk.custom;

import com.hjk.custom.service.ProductService;
import com.hjk.custom.service.UserService;
import com.hjk.custom.utils.PrintUtils;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;


	@Test
	@Transactional
	public void Test1() {
		userService.save("H2");
		var data = userService.getUsers();
		PrintUtils.print(data);
	}

	@Test
	public void Test2() {
		var user = userService.getUser("H2");
		var product = productService.save("apple", user);
		PrintUtils.print(product);
	}

	@Test
	@Transactional
	public void Test3() {
		var user = userService.getUser(2L);
		System.out.println(user);
	}

}
