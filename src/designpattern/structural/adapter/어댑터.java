package designpattern.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 어댑터 패턴은 직접 연결할 수 없는 두 개의 호환되지 않는 인터페이스 간의 커넥터 역할을 합니다. 
 * 기존 소스 코드를 수정하지 않고 기존 클래스가 다른 클래스와 함께 작동하도록 만드는 데 자주 사용됩니다.
 * 이 패턴의 주요 목표는 기존 인터페이스를 클라이언트가 기대하는 다른 인터페이스로 변환하는 것입니다.
 * 이 패턴의 구조는 Decorator와 유사합니다. 그러나 데코레이터는 일반적으로 확장을 염두에 두고 구현됩니다. 
 * 어댑터는 일반적으로 호환되지 않는 인터페이스를 연결하기 위해 초기 코드가 작성된 후에 구현됩니다. 
 * 
 * 타사 라이브러리를 사용하는 대부분의 응용 프로그램은 어댑터를 응용 프로그램과 
 * 타사 라이브러리 사이의 중간 계층으로 사용하여 라이브러리에서 응용 프로그램을 분리합니다. 
 * 다른 라이브러리를 사용해야 하는 경우 애플리케이션 코드를 변경하지 않고 새 라이브러리에 대한 어댑터만 필요합니다.
 * 이 패턴을 구현하는 두 가지 주요 방법
 * 클래스 어댑터 : 상속으로 구현한다. 하지만 자바는 다중 상속 미지원 언어로 사용하지 않는다.
 * 오브젝트 어댑터 : 구성으로 구현한다.
 * 
 * Target Interface : 클라이언트가 사용하기 원하는 인터페이스 클래스입니다.
 * Adapter class: 이 클래스는 원하는 Target Interface를 구현하고 Adaptee 클래스에서 사용 가능한 특정 요청을 수정하는 래퍼 클래스입니다.
 * Adaptee class : 기존 기능을 재사용하고 원하는 용도로 수정하기 위해 어댑터 클래스에서 사용하는 클래스입니다.
 * Client: 이 클래스는 어댑터 클래스와 상호 작용합니다.
 * 
 * 만약 외부 라이브러리를 사용할 때 어뎁터를 이용한다면, 외부 라이브러리 인터페이스를 숨겨 의존성을 낮출 수 있다.
 * 
 * </pre>
 *
 */
public class 어댑터 {
	
	//target Interface
	static interface 통합계좌서비스{
		int 예금조회();
		int 계좌조회();
		String 소유주조회();
	}
	
	//adaptee
	static class 신한은행 {
		int money;
		int acount;
		String name;
		
		public 신한은행(int money, int acount, String name) {
			this.money = money;
			this.acount = acount;
			this.name = name;
		}
		public int 통장돈확인하기() {
			return this.money;
		}
		public int 계좌번호조회하기() {
			return this.acount;
		}
		public String 소유자확인하기() {
			return this.name;
		}
		@Override
		public String toString() {
			return "신한은행 [money=" + money + ", acount=" + acount + ", name=" + name + "]";
		}
		
	}
	//Adaptee
	static class 국민은행 {
		int money;
		int acount;
		String name;
		
		public 국민은행(int money, int acount, String name) {
			this.money = money;
			this.acount = acount;
			this.name = name;
		}
		public int 예금확인하기() {
			return this.money;
		}
		public int 계좌조회하기() {
			return this.acount;
		}
		public String 예금주확인하기() {
			return this.name;
		}
		@Override
		public String toString() {
			return "국민은행 [money=" + money + ", acount=" + acount + ", name=" + name + "]";
		}
		
	}
	
	//객체 어뎁터 방식, 구성과 위임, 여러 개도 가능은 함
	static class 신한은행어댑터 implements 통합계좌서비스{
		private 신한은행 bank;
		
		public 신한은행어댑터(신한은행 bank) {
			super();
			this.bank = bank;
		}
		@Override
		public int 예금조회() {
			return this.bank.통장돈확인하기();
		}
		@Override
		public int 계좌조회() {
			return this.bank.계좌번호조회하기();
		}
		@Override
		public String 소유주조회() {
			return this.bank.소유자확인하기();
		}
		@Override
		public String toString() {
			return bank.toString();
		}
	}
	//클래스 어댑터 방식, 자바는 단일상속 언어라 선호되지 않는 방식
	static class 국민은행어댑터 extends 국민은행 implements 통합계좌서비스{
		public 국민은행어댑터(int money, int acount, String name) {
			super(money, acount, name);
		}
		@Override
		public int 예금조회() {
			return this.예금확인하기();
		}
		@Override
		public int 계좌조회() {
			return this.계좌조회하기();
		}
		@Override
		public String 소유주조회() {
			return this.예금주확인하기();
		}
		
	}
	
	public static void main(String[] args) {
		List<통합계좌서비스> list = new ArrayList<>();
		신한은행어댑터 a1 = new 신한은행어댑터(new 신한은행(10000, 12345, "홍길동"));
		국민은행어댑터 a2 = new 국민은행어댑터(10000, 54321, "임꺽정");
		
		list.add(a1);
		list.add(a2);
		
		for(통합계좌서비스 a : list) {
			System.out.println(a);
		}
		
		
	}
	
}
