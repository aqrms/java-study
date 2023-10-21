package singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

	private static final SingletonService instance = new SingletonService();

	public static SingletonService getInstance(){
		return instance;
	}

	private SingletonService(){
	}

	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletoneServiceTest(){
		SingletonService instance1 = SingletonService.getInstance();
		SingletonService instance2 = SingletonService.getInstance();

		System.out.println("instance1 = " + instance1);
		System.out.println("instance2 = " + instance2);
	}
}
