package javabasic.lang.math;

public class Math01 {
	public static void main(String[] args) {
		Double d = 123.456789;
		System.out.println(round(d, 0));
		System.out.println(round(d, -2));
		System.out.println(round(d, 2));
	}
	static Double round(Number num, int position) {
		Double po = position>=0?1D:-1D;
		for(int i=0;i<Math.abs(position);i++) {
			po*=10D;
		}
		Double result = 0.0;
		if(position>=0) {
			result = Math.round(num.doubleValue()*po)/po;
		}else {
			result = Math.round(num.doubleValue()/po)*po;
		}
		return Double.valueOf(result); 
	}
}