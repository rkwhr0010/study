package designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
개체를 트리 구조로 구성하여 부분-전체 계층을 나타냅니다.
Composite를 사용하면 클라이언트가 개별 개체와 개체 구성(컴포지트)을 균일하게 처리할 수 있습니다.
합성 패턴은 개별 개체와 개체의 구성 또는 "합성물"을 동일한 방식으로 처리할 수 있도록 하기 위한 것입니다.
기본 유형을 상속하는 유형으로 구성된 트리 구조로 볼 수 있으며 개체의 단일 부분 또는 전체 계층 구조를 나타낼 수 있습니다.
 
    component  – 컴포지션의 모든 개체에 대한 기본 인터페이스입니다. 하위 조합을 관리하는 공통 메서드가 있는 인터페이스 또는 추상 클래스여야 합니다.
	leaf  – 기본 component의 기본 동작을 구현합니다. 다른 개체에 대한 참조(다른 leaf)를 포함하지 않습니다.
	composite  – leaf 요소가 있습니다. 기본 구성요소 메서드를 구현하고 하위 관련 작업을 정의합니다.
	client  – 기본 구성 요소 개체를 사용하여 컴포지션 요소에 액세스할 수 있습니다.
 * </pre>
 */
public class 컴포지트 {
	//컴포넌트
	static interface Department{
		void printDepartmentName();
	}
	//리프
	static class FinancialDepartment implements Department{
		private Integer id;
		private String name;
		
		public FinancialDepartment(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
		public void printDepartmentName() {
			System.out.println(this.getClass().getSimpleName());
		}
		public Integer getId() {return id;	}
		public void setId(Integer id) {	this.id = id;}
		public String getName() {return name;}
		public void setName(String name) {	this.name = name;	}
	}
	static class SalesDepartment implements Department{
		private Integer id;
		private String name;
		
		public SalesDepartment(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
		public void printDepartmentName() {
			System.out.println(this.getClass().getSimpleName());
		}
		public Integer getId() {return id;	}
		public void setId(Integer id) {	this.id = id;}
		public String getName() {return name;}
		public void setName(String name) {	this.name = name;	}
	}
	/**
	 * composite
	 * 이 클래스는 Department 구성 요소 컬렉션과 목록에서 요소를 추가하고 제거하는 메서드를 포함하는 복합 클래스입니다.
	 * 복합 printDepartmentName() 메서드는 리프 요소 목록을 반복하고 각각에 대해 적절한 메서드를 호출하여 구현됩니다.
	 */
	static class HeadDepartment implements Department{
		private Integer id;
		private String name;
		
		private List<Department> childDepartments;

		public HeadDepartment(Integer id, String name) {
			this.id = id;
			this.name = name;
			this.childDepartments = new ArrayList<>();
		}

		public void printDepartmentName() {
			childDepartments.forEach(Department::printDepartmentName);
		}
		
		public void addDepartment(Department department) {
			if(department instanceof HeadDepartment) throw new IllegalArgumentException();
			childDepartments.add(department);
		}
		public void removeDepartment(Department department) {
			childDepartments.remove(department);
		}
		
	}
	//클라이언트. 별도 클래스로 다뤄야 한다. 편의상 이 클래스에서 다뤘음
	public static void main(String[] args) {
		Department salesDepartment = new SalesDepartment(1, "판매 부서");
		Department financialDepartment = new FinancialDepartment(2, "재무 부서");
		
		HeadDepartment headDepartment = new HeadDepartment(3, "본부 부서");
		headDepartment.addDepartment(salesDepartment);
		headDepartment.addDepartment(financialDepartment);
//		headDepartment.addDepartment(headDepartment);
		
		headDepartment.printDepartmentName();
		
	}
	
}
