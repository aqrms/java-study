package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class AppContextExtendsFindTest {

	@Test
	@DisplayName("부모 타입 조회, 자식 둘 이상 = 중복오류")
	void findBeanByParentTypeDup(){
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(DiscountPolicy.class));
	}
	@Test
	@DisplayName("자식 둘이상있으면, 빈 이름을 지정")
	void findBeanByParentTypeBeanName(){
		DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}
	@Test
	@DisplayName("부모타입으로 모두 조회")
	void findAllBeanByParent(){
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		for (String key: beansOfType.keySet()){
			System.out.println("key + beansOfType.get(key) = " + key + beansOfType.get(key));
		}
	}
	@Test
	@DisplayName("부모타입으로 모두 조회")
	void findAllBeanByObject() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key + beansOfType.get(key) = " + key + beansOfType.get(key));
		}
	}


	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@Configuration
	static class TestConfig{
		@Bean
		public DiscountPolicy rateDiscountPolicy(){
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy(){
			return new FixDiscountPolicy();
		}
	}
}
