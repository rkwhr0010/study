package algorithm.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
모든 아나그램 찾기(해쉬, 투포인터, 슬라이딩 윈도우)
S문자열에서 T문자열과 아나그램이 되는 S의 부분문자열의 개수를 구하는 프로그램을 작성하
세요. 아나그램 판별시 대소문자가 구분됩니다. 부분문자열은 연속된 문자열이어야 합니다.
▣ 입력설명
첫 줄에 첫 번째 S문자열이 입력되고, 두 번째 줄에 T문자열이 입력됩니다.
S문자열의 길이는 10,000을 넘지 않으며, T문자열은 S문자열보다 길이가 작거나 같습니다.
▣ 출력설명
S단어에 T문자열과 아나그램이 되는 부분문자열의 개수를 출력합니다.
▣ 입력예제 1
bacaAacba
abc
▣ 출력예제 1
3
출력설명: {bac}, {acb}, {cba} 3개의 부분문자열이 "abc"문자열과 아나그램입니다.
▣ 입력예제 2
bacaAacbaa
abca
▣ 출력예제 2
3
 */
public class 모든아나그램찾기 {
	static String s;
	static String t;
	
	public static void main(String[] args) {
		input();
		
		int result = solution();
		System.out.println(result);
	}
	private static int solution() {
		int result = 0;
		Map<Character, Integer> tMap = new HashMap<>();
		Map<Character, Integer> sMap = new HashMap<>();
		
		for(Character cha : t.toCharArray()) {
			tMap.put(cha, tMap.getOrDefault(cha, 0)+1);
		}
		
		for(int i = 0; i < t.length()-1; i++) {
			sMap.put((s.charAt(i)), sMap.getOrDefault((s.charAt(i)), 0)+1);
		}
		
		for(int lt = 0, rt = t.length() - 1; rt < s.length(); rt++,lt++) {
			sMap.put(s.charAt(rt), sMap.getOrDefault(s.charAt(rt), 0)+1);
			if(tMap.equals(sMap)) result++;
			sMap.put(s.charAt(lt), sMap.get(s.charAt(lt))-1);
			if (sMap.get(s.charAt(lt)).intValue() == 0 ) sMap.remove(s.charAt(lt));
		}
		
		return result;
	}
	private static void input() {
		Scanner sc = new Scanner(System.in);
		s = sc.next();
		t = sc.next();
		sc.close();
	}
}
