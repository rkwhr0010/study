package designpattern.behavioral.strategy;

public class 계산기{
	
	//전략
	static interface Calculator{
		<T extends Number> T calculate(T a, T b, Class<T> type);
	}
	
	static class Addition implements Calculator{
		public <T extends Number> T calculate(T a, T b, Class<T> type) {
			return type.cast(a.longValue()+b.longValue());
		}
	}
	static class Subtraction  implements Calculator{
		public <T extends Number> T calculate(T a, T b, Class<T> type) {
			return type.cast(a.longValue()-b.longValue());
		}
	}
	static class Multiplication implements Calculator{
		public <T extends Number> T calculate(T a, T b, Class<T> type) {
			return type.cast(a.longValue()*b.longValue());
		}
	}
//	static class Division implements Calculator<Double>{
//		public Double calculate(Double a, Double b) {
//			return a/b;
//		}
//	}
//	
//	static class Computer<T extends Number> {
//		Calculator<T> calculator;
//		public Computer() {}
//		public Computer(Calculator<T> calculator) {
//			this.calculator = calculator;
//		}
//		
//		public Calculator<T> getCalculator() {
//			return calculator;
//		}
//
//		public void setCalculator(Calculator<T> calculator) {
//			this.calculator = calculator;
//		}
//
//		public T calculation(T a,T b) {
//			return (T)calculator.calculate(a, b);
//		}
//		
//	}
//	public static void main(String[] args) {
//		Computer computer = new Computer();
//		Calculator<Long> add = new Addition();
//		Calculator<Long> sub = new Subtraction();
//		Calculator<Long> mul = new Multiplication();
//		Calculator<Double> div = new Division();
//		computer.setCalculator(add);
//		System.out.println(computer.calculation(12345L, 123L));
//		computer.setCalculator(sub);
//		System.out.println(computer.calculation(12345L, 123L));
//		computer.setCalculator(mul);
//		System.out.println(computer.calculation(12345L, 123L));
//		computer.setCalculator(div);
//		System.out.println(computer.calculation(12345D, 123D));
//		
//	}
	
	
}
