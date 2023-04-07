package javabasic.ifelseif;

public class IfTest {
	enum Te{
		A(()->{
			System.out.println("AAAAA");
		})
		,B(()->{
			System.out.println("BBBBB");
		})
		,C(()->{
			System.out.println("CCCCC");
		});
		
		private Te(Runnable run) {
			this.run = run;
		}

		Runnable run;
	}
	
	static void operation(Te t) {
		switch (t) {
		case A:
			t.run.run();
			break;
		case B:
			t.run.run();
			break;
		case C:
			break;
		}
	}
	
	
	public static void main(String[] args) {
		
		
	}
	
	
	
}
