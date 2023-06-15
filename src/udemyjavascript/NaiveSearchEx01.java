package udemyjavascript;

public class NaiveSearchEx01 {
	public static void main(String[] args) {
		int count = count("abcaa134abc1aaaabc","abc");
		System.out.println(count);
		
	}
	static int count(String longStr, String shortStr) {
		int result = 0;
		char[] lc = longStr.toCharArray();
		char[] sc = shortStr.toCharArray();
		
		for(int i=0;i < lc.length ;i++) {
			for(int j=0;j<sc.length;j++) {
				if(lc[i+j]!=sc[j]) break;
				else if(j==sc.length-1) result++;
			}
		}
		return result;
	}
}
