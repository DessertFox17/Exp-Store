package com.experimentality.Store;

import com.experimentality.Store.domain.service.ProductServiceShould;
import com.experimentality.Store.domain.service.UserServiceShould;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ProductServiceShould.class,
		UserServiceShould.class
})
@SpringBootTest
public class StoreTestExpApplicationTests {

	@Test
	public void contextLoads() {
	}

}
