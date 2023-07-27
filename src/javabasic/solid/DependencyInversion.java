package javabasic.solid;
/*
 * 의존성 역전 원칙은 소프트웨어 모듈 분리를 의미합니다.
 * 이 원칙을 지키면, 하위 수준 모듈에 의존한 상위 수준 모듈이
 * 사이에 추상화된 인터페이스에 의존하게 됩니다.
 * 
 * 결과적으로 하위 수준 모듈의 변경이 상위 수준 모듈 변경 관계를 끊게 됩니다.
 * 
 * 다시 말하면, 상위 레벨의 레이어가 하위 레벨의 레이어를 
 * 바로 의존하게 하는 것이 아니라 이 둘 사이에 존재하는 추상레벨을 통해 의존해야 할 것
 * 
 */
public class DependencyInversion {
	
	
}

/* Dependency Inject 사례
 * 구체적인 게임에 의존하고 있다.
 * new 연산자는 인터페이스(또는 추상 클래스)에 사용할 수 없다.
 */
class StarCraft{}
class Player {
	StarCraft starCraft;

	public Player() {
		this.starCraft = new StarCraft();
	}
}
//상위 추상 인터페이스를 구현하고,
//인터페이스로 클래스를 만들 수 없으니 의존 주입을 받는다.
interface OnlineGame {}
class StarCraft2 implements OnlineGame{}

class Player2 {
	OnlineGame onlineGame;
	
	public Player2(OnlineGame onlineGame) {
		this.onlineGame = onlineGame;
	}
}
