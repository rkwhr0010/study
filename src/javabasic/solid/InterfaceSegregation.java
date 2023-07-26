package javabasic.solid;
/*
 * SOLID의 I는 인터페이스 분리를 ​​나타내며 단순히 더 큰 인터페이스를 
 * 더 작은 인터페이스로 분할해야 함을 의미합니다. 
 * 그렇게 함으로써 우리는 구현 클래스가 그들에게 
 * 관심 있는 메소드에 대해서만 관심을 가지면 된다는 것을 확신할 수 있습니다.
 * 
 * ISP원리는 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다는 원리입니다. 
 * 즉 어떤 클래스가 다른 클래스에 종속될 때에는 가능한 최소한의 인터페이스만을 사용해야 합니다.
 * ISP를 ‘하나의 일반적인 인터페이스보다는, 여러 개의 구체적인 인터페이스가 낫다’라고 정의할 수 도 있습니다.
 * 
 * 만약 어떤 클래스를 이용하는 클라이언트가 여러 개고 
 * 이들이 해당 클래스의 특정 부분집합만을 이용한다면, 
 * 이들을 따로 인터페이스로 빼내어 
 * 클라이언트가 기대하는 메시지만을 전달할 수 있도록 합니다. 
 * 
 * SRP가 클래스의 단일책임을 강조한다면 ISP는 인터페이스의 단일책임을 강조합니다. 
 * 하지만 ISP는 어떤 클래스 혹은 인터페이스가 여러 책임 혹은 역할을 갖는 것을 인정합니다. 
 * 이러한 경우 ISP가 사용되는데 SRP가 클래스 분리를 통해 변화에의 적응성을 획득하는 반면, 
 * ISP에서는 인터페이스 분리를 통해 같은 목표에 도달 합니다.
 */
public class InterfaceSegregation {
	/* 다소 과격한 예시, 구도만 인식할 것
	 * 거대한 인터페이스 CRUD 전부 가능하다.
	 */
	interface CrudDatabase{
		void select();
		void delete();
		void update();
		void insert();
	}
	
	class DatabaseController implements CrudDatabase{
		public void select() {	}
		public void delete() {	}
		public void update() {	}
		public void insert() {	}
	}
	
	/*
	 * 작은 인터페이스로 분할하고, 
	 * 각 기능에 관심있는 사용자에게 그 인터페이스만 다루도록 한다.
	 * 다른 기능 노출을 막을 수 있다.
	 */
	interface CreateDatabase{
		void insert();
	}
	interface ReadDatabase{
		void select();
	}
	interface UpdateDatabase{
		void update();
	}
	interface DeleteDatabase{
		void delete();
	}
	
	class DatabaseController2 implements CreateDatabase
	, ReadDatabase, UpdateDatabase, DeleteDatabase{
		public void select() {	}
		public void delete() {	}
		public void update() {	}
		public void insert() {	}
	}
}

