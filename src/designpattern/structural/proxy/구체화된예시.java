package designpattern.structural.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 프록시는 다른 객체에 대한 대체 또는 자리표시자를 제공할 수 있는 구조 디자인 패턴입니다. 
 * 프록시는 원래 객체에 대한 접근을 제어하므로, 
 * 당신의 요청이 원래 객체에 전달되기 전 또는 후에 무언가를 수행할 수 있도록 합니다.
 * 
 * 어댑터는 다른 인터페이스를, 프록시는 같은 인터페이스를, 데코레이터는 향상된 인터페이스를 래핑된 객체에 제공합니다.
 * 
 * 주 사용은 지연로딩, 캐싱, 원격프록시, 접근제어
 * 
 * #구성요소
 * ServiceInterface
 * 프록시할 대상 서비스의 인터페이스
 * 서비스 인터페이스는 서비스의 인터페이스를 선언합니다. 
 * 프록시가 서비스 객체로 위장할 수 있으려면 이 인터페이스를 따라야 합니다.
 * 
 * Service
 * 간접적으로 다루기 위한 프록시 대상 서비스
 * 서비스는 어떤 유용한 비즈니스 로직을 제공하는 클래스입니다.
 * 
 * Proxy
 * 프록시 클래스에는 서비스 객체를 가리키는 참조 필드가 있습니다. 
 * 프록시가 요청의 처리​(예: 초기화 지연, 로깅, 액세스 제어, 캐싱 등)​를 완료하면,
 * 그 후 처리된 요청을 서비스 객체에 전달합니다.
 * 일반적으로 프록시들은 서비스 객체들의 전체 수명 주기를 관리합니다.
 * 
 */
public class 구체화된예시 {
	public static void main(String[] args) {
		RandomGenerator loggingProxy = new NumberLoggingProxy(new IntRandomGenerator());
		
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
		System.out.println("  횟수 : " + loggingProxy.getRandomNumber());
	}
}

//ServiceInterface
interface RandomGenerator{
	Number getRandomNumber();
}
//Service
class IntRandomGenerator implements RandomGenerator{
	public Integer getRandomNumber() {
		return new Random().nextInt(1,5);
	}
}
//Proxy, 똑같은 값이 몇번 생성 됐는지 체크
class NumberLoggingProxy implements RandomGenerator{
	private final RandomGenerator randomGenerator;
	Map<Integer, Integer> countMap = new HashMap<>();
	
	public NumberLoggingProxy(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}
	public Integer getRandomNumber() {
		Integer randomNumber = randomGenerator.getRandomNumber().intValue();
		System.out.print("랜덤 값 : " +randomNumber);
		countMap.compute(randomNumber, (k, cnt) -> {
			if(cnt == null) return 1;
			else return cnt + 1;
		});
		return countMap.get(randomNumber);
	}
}

