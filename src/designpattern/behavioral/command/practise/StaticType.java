package designpattern.behavioral.command.practise;

public class StaticType {
	public static void main(String[] args) {
		Character a = '1';
		Character b = a;
		b = '2';
		
		
		System.out.println(a);
		System.out.println(b);
		
		
	}
}
