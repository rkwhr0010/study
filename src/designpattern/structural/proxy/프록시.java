package designpattern.structural.proxy;
/**
 * 프록시 패턴을 사용하면 구성 요소의 기본 복잡성을 숨기면서 다른 리소스에 대한 인터페이스 역할을 하는 중개자를 만들 수 있습니다.
 * 
 * 간단히 프록시는 다른 개체를 나타내는 개체를 의미합니다.

GoF에 따르면 프록시 패턴은 "원본 개체에 액세스하기 위한 제어를 제공합니다".

따라서 원래 개체의 정보 숨기기, 요청 시 로드 등과 같은 많은 작업을 수행할 수 있습니다.

프록시 패턴은 Surrogate 또는 Placeholder라고도 합니다.

외부 세계로부터 원래 개체를 보호합니다.


가장 일반적인 형태의 프록시는 다른 항목에 대한 인터페이스 역할을 하는 클래스입니다. 
프록시는 배후에서 실제 제공 개체에 액세스하기 위해 클라이언트가 호출하는 래퍼 또는 에이전트 개체입니다. 
프록시를 사용하면 단순히 실제 개체로 전달하거나 추가 논리를 제공할 수 있습니다. 
프록시에서 추가 기능을 제공할 수 있습니다. 
예를 들어 실제 개체에 대한 작업이 리소스 집약적인 경우 캐싱하거나 실제 개체에 대한 작업이 호출되기 전에 전제 조건을 확인합니다.
 *

프록시는 단순한 포인터보다 개체에 대한 더 다양하고 정교한 참조가 필요할 때마다 적용할 수 있습니다. 
프록시 패턴을 적용할 수 있는 몇 가지 일반적인 상황은 다음과 같습니다.

원격(Remote) 프록시는 다른 주소 공간에 있는 개체에 대한 로컬 대표를 제공합니다.
	원격 프록시는 RPC 호출의 스텁에 대해 생각할 수 있습니다. 
	원격 프록시는 다른 주소 위치에 있는 개체의 로컬 표현을 제공합니다. 
	또 다른 예로 웹 서비스 또는 REST 리소스와 같은 원격 리소스에 대한 인터페이스를 제공할 수 있습니다.
	
가상(Virtual) 프록시는 요청 시 값비싼 개체를 생성합니다.
	거대한 크기의 이미지를 추출하기 위해 여러 데이터베이스 호출이 있는 상황을 고려하십시오. 
	이것은 비용이 많이 드는 작업이므로 여기서는 프록시 패턴을 사용하여 여러 프록시를 생성하고 
	추가 처리를 위해 거대한 크기의 메모리 소비 개체를 가리킬 수 있습니다. 
	실제 객체는 클라이언트가 객체를 처음 요청/액세스할 때만 생성되며 
	그 후에 객체를 재사용하기 위해 프록시를 참조할 수 있습니다. 
	이렇게 하면 개체의 복제를 방지하여 메모리를 절약할 수 있습니다.
	
보호(Protection) 프록시는 원본 개체에 대한 액세스를 제어합니다. 
	보호 프록시는 객체가 다른 액세스 권한을 가져야 하는 경우에 유용합니다.
	실제 사용자가 적절한 콘텐츠에 액세스할 수 있는지 여부를 확인하는 인증 계층 역할을 합니다. 
	예를 들어 사무실에서 인터넷 액세스를 제한하는 프록시 서버입니다. 
	유효한 웹사이트와 콘텐츠만 허용되고 나머지는 차단됩니다.
 */
public class 프록시 {
	
	static interface ExpensiveObject {
	    void process();
	}
	
	static class ExpensiveObjectImpl implements ExpensiveObject {
	    public ExpensiveObjectImpl() {
	        heavyInitialConfiguration();
	    }
	    public void process() {
	        System.out.println("처리 완료.");
	    }
	    //초기화 비용이 아주 큼
	    private void heavyInitialConfiguration() {
	    	System.out.println("초기 구성 작업 중...");
	    }
	}
	
	static class ExpensiveObjectProxy implements ExpensiveObject {
		//초기화 비용이 커서 매번 새로운 인스턴스 생성은 낭비
	    private static ExpensiveObject object;
	    public void process() {
	    	//지연초기화 방식
	        if (object == null) {
	        	object = new ExpensiveObjectImpl();
	        }
	        object.process();
	    }
	}
	
	public static void main(String[] args) {
		ExpensiveObject object = new ExpensiveObjectProxy();
	    object.process();
	    object.process();
	}
}
