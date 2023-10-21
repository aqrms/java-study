package singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		MemberService memberService1 = appConfig.memberService();
		MemberService memberService2 = appConfig.memberService();

		System.out.println("memberService1 != 2 " + (memberService1 != memberService2));
		System.out.println("memberService2 = " + memberService2);

	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);

		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isSameAs(memberService2);
	}
}


