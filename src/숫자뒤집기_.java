public class 숫자뒤집기_ {
	public static void main(String[] args) {
		int value = 12345;
		int rest = 0;
		int count = 0;
		//value의 일의 자리를 계속 자르고 맨 앞으로 보낸다.
		while(value>0) {
			int units = value % 10 ; // 일의 자리 구하기
			// 일의 자리를 계속 10곱하기 반복, 결국 뒤집기가 된다.
			rest = rest * 10 + units;
			System.out.println(value+" "+ ++count + "회 " + rest  
					+" | 일의 자리를 *10 하다보면 뒤집어진다.");
			value = value / 10;		 // 십의 자리만큼 제거
		}
		value = rest;
	}
}
