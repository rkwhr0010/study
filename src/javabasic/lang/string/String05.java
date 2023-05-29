package javabasic.lang.string;

public class String05 {
    public static void main(String[] args) throws Exception {
    	String val = "문자열 연산";
    	Integer num = 1000;
    	
    	//기본형 + 문자열 결합일 땐 + 연결도 별차이 없을 듯
    	String str = num + val;            // 서로 같다
    	String str2 = num.toString() + val;// 서로 같다
    	String str3  = String.valueOf(num) + val;
    	//단, 기본형에서 문자열로 변환시는 차이가 좀 있을 지도
    	String str4 = num + "";
    	String str5 = num.toString()+"";
    	String str6 = String.valueOf(num);
    }
}