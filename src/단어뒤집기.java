public class 단어뒤집기 {
	public static void main(String[] args) {
		String word = "가나다라마바사";
		int lt = 0;
		int rt = word.length()-1;
		char[] cs = word.toCharArray();
		char[] cs2 = word.toCharArray();
		char tmp = ' ';
		
		//방법 1
		while(lt<rt) {
			tmp = cs[lt];     
			cs[lt] = cs[rt];  
			cs[rt] = tmp;     
			lt++;
			rt--;
		}
		
		//방법 2
		int len = cs2.length;
		for(int i=0; i<len/2;i++) {
			tmp = cs2[i];
			cs2[i] = cs2[len-i-1];
			cs2[len-i-1] = tmp;
		}
		
		System.out.println(String.valueOf(cs));
		System.out.println(String.valueOf(cs2));
		
	}
}
