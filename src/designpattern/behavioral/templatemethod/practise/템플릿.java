package designpattern.behavioral.templatemethod.practise;

public class 템플릿 {
	static abstract class Computer{
		
		public void build() {
			motherBorad();
			mainMemory();
			subMemory();
			power();
			hook();
		}

		protected abstract void hook();

		protected abstract void power();

		private void subMemory() {
			System.out.println("ram assemble");
		}

		private void mainMemory() {
			System.out.println("nvme assemble");
		}

		private void motherBorad() {
			System.err.println("motherBorad assemble");
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
	}
}
