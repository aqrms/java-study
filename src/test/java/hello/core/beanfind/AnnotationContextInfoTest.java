package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class AnnotationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든빈출력")
	void findallBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("beanDefinitionName  = " + beanDefinitionName + "object =" + bean);

		}
	}

	@Test
	@DisplayName("앱 빈 출력")
	void findAppBean() {
		String[] beanDefNames = ac.getBeanDefinitionNames();
		for (String beanDefName : beanDefNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefName);
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefName);
				System.out.println("beanDefinitionName  = " + beanDefName + "object =" + bean);
			}
		}
	}
}
