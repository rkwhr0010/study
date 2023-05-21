package designpattern.headfirst.chapter7;

public class 최소지식 {
	static class A1{
		A2 a2 = new A2();
		void callA1() {System.out.println("A1 야호!");}
		void callA2() {a2.callA2();}
		void callA3() {a2.a3.callA3();}
	}
	static class A2{
		A3 a3 = new A3();
		void callA2() {System.out.println("A2 야호!");}
	}
	static class A3{
		void callA3() {System.out.println("A3 야호!");}
	}
	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.callA1();
		a1.callA2();
		a1.a2.callA2();
		a1.callA3();
		a1.a2.a3.callA3();
	}
	
}
