package designpattern.structural.abstractfactory;

import designpattern.structural.abstractfactory.KingdomFactoryProvider.KingdomType;

/**
 * <pre> 
 * 추상 팩토리 패턴은 구체적인 클래스를 지정하지 않고 공통 주제를 가진 개별 팩토리 그룹을 캡슐화하는 방법을 제공합니다.
 * 
 * 추상 팩토리 패턴은 구체적인 하위 클래스를 지정하지 않고 관련(또는 종속) 객체의 패밀리를 생성하기 위한 인터페이스 또는 추상 클래스를 정의하는 것을 의미합니다. 
 * 즉, 추상 팩토리는 클래스가 클래스의 팩토리를 반환하도록 합니다. 그래서 Abstract Factory Pattern이 Factory Pattern보다 한 단계 높은 이유입니다.
 * </pre>
 */
public class 추상팩토리 {
	public static void main(String[] args) {
		KingdomFactory elfFactory = KingdomFactoryProvider.makeFactory(KingdomType.ELF);
		KingdomFactory orcFactory = KingdomFactoryProvider.makeFactory(KingdomType.ORC);
		System.out.println(elfFactory.createCastle().getDescription());
		System.out.println(elfFactory.createKing().getDescription());
		System.out.println(elfFactory.createArmy().getDescription());
		System.out.println(orcFactory.createCastle().getDescription());
		System.out.println(orcFactory.createKing().getDescription());
		System.out.println(orcFactory.createArmy().getDescription());
	}
}
// 클래스 군(그룹) 추상화
interface Castle{String getDescription();}
interface King{String getDescription();}
interface Army{String getDescription();}
//클래스 그룹1
class ElfCastle implements Castle{
	private static final String DESCRIPTION = "엘프의 성!";
	@Override
	public String getDescription() {
		return ElfCastle.DESCRIPTION;
	}
}
class ElfKing implements King{
	private static final String DESCRIPTION = "엘프의 왕!";
	@Override
	public String getDescription() {
		return ElfKing.DESCRIPTION;
	}
}
class ElfArmy implements Army{
	private static final String DESCRIPTION = "엘프의 군대!";
	@Override
	public String getDescription() {
		return ElfArmy.DESCRIPTION;
	}
}
//클래스 그룹2
class OrcCastle implements Castle{
	private static final String DESCRIPTION = "오크의 성!";
	@Override
	public String getDescription() {
		return OrcCastle.DESCRIPTION;
	}
}
class OrcKing implements King{
	private static final String DESCRIPTION = "오크의 왕!";
	@Override
	public String getDescription() {
		return OrcKing.DESCRIPTION;
	}
}
class OrcArmy implements Army{
	private static final String DESCRIPTION = "오크의 군대!";
	@Override
	public String getDescription() {
		return OrcArmy.DESCRIPTION;
	}
}

//클래스 군을 생성할 추상 팩토리 정의
interface KingdomFactory{
	Castle createCastle();
	King createKing();
	Army createArmy();
}
//추상팩토리 구현 1
class ElfKingdomFactory implements KingdomFactory{
	public Castle createCastle() {
		return new ElfCastle();
	}
	public King createKing() {
		return new ElfKing();
	}
	public Army createArmy() {
		return new ElfArmy();
	}
}
//추상팩토리 구현 2
class OrcKingdomFactory implements KingdomFactory{
	public Castle createCastle() {
		return new OrcCastle();
	}
	public King createKing() {
		return new OrcKing();
	}
	public Army createArmy() {
		return new OrcArmy();
	}
}

class KingdomFactoryProvider {
	private KingdomFactoryProvider() {}
	public enum KingdomType{
		ORC,ELF
	}
	public static KingdomFactory makeFactory(KingdomType type) {
		switch (type) {
		case ORC:
			return new OrcKingdomFactory();
		case ELF:
			return new ElfKingdomFactory();
		default:
			throw new IllegalArgumentException();
		}
	}
}



