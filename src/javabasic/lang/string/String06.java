package javabasic.lang.string;

public class String06 {
    public static void main(String[] args) throws Exception {
    	System.out.println(Long.valueOf(" 1000 ".trim())); // 공백있으면 예외 발생
    	System.out.println(Double.valueOf("1000.")); // 실수 정수 끝에 .은 허용
    	System.out.println(Integer.valueOf("+1000"));//부호 허용
    	System.out.println(Long.valueOf("-1000")); //부호 허용
    	System.out.println(Float.valueOf("-1000.")); //부호 허용
    }
}