package javabasic.lang.object;

import java.util.Arrays;

public class Clone01 {
    public static void main(String[] args) throws CloneNotSupportedException {
    	Value v1 = new Value(10, 100L, "값", new int[] {1,2,3});
    	Value v2 = v1.clone();
    	System.out.println(v1);
    	System.out.println(v2);
    	v2.intV = 123;
    	v2.longV = 1234L;
    	v2.strV = "바꿈";
    	v2.arrInt[0] = 4;
    	v2.arrInt[1] = 5;
    	v2.arrInt[2] = 6;
    	System.out.println(v1);
    	System.out.println(v2);
    	
    }
    //제약 Cloneable 구현안하고 clone()호출 시 예외 발생
    static class Value implements Cloneable{
    	int intV;
    	Long longV;//오토박싱
    	String strV;
    	int[] arrInt;
		public Value(int intV, Long longV, String strV, int[] arrInt) {
			super();
			this.intV = intV;
			this.longV = longV;
			this.strV = strV;
			this.arrInt = arrInt;
		}
		@Override //기본적으로 protected 로 재정의해야 사용가능
		public Value clone() throws CloneNotSupportedException {
			return (Value) super.clone();
		}
		@Override
		public String toString() {
			return "Value [intV=" + intV + ", longV=" + longV + ", strV=" + strV + ", arrInt=" + Arrays.toString(arrInt)
					+ "]";
		}
    }
}
