package singleton;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
	@Test
	void statefulServiceSingleton(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(
			TestConfig.class);

		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		statefulService1.order("UserA",10000);
		statefulService2.order("UserA",20000);

		int price = statefulService1.getPrice();
		System.out.println("price1 = " + price);

		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig{
		@Bean
		public StatefulService statefulService(){
			return new StatefulService();
		}
	}
}
