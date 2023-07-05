package algorithm.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/*
아나그램(해쉬)
Anagram이란 두 문자열이 알파벳의 나열 순서를 다르지만 그 구성이 일치하면 두 단어는 아
나그램이라고 합니다.
예를 들면 AbaAeCe 와 baeeACA 는 알파벳을 나열 순서는 다르지만 그 구성을 살펴보면
A(2), a(1), b(1), C(1), e(2)로 알파벳과 그 개수가 모두 일치합니다. 즉 어느 한 단어를 재
배열하면 상대편 단어가 될 수 있는 것을 아나그램이라 합니다.
길이가 같은 두 개의 단어가 주어지면 두 단어가 아나그램인지 판별하는 프로그램을 작성하세
요. 아나그램 판별시 대소문자가 구분됩니다.
▣ 입력설명
첫 줄에 첫 번째 단어가 입력되고, 두 번째 줄에 두 번째 단어가 입력됩니다.
단어의 길이는 100을 넘지 않습니다.
▣ 출력설명
두 단어가 아나그램이면 “YES"를 출력하고, 아니면 ”NO"를 출력합니다.
▣ 입력예제 1
AbaAeCe
baeeACA
▣ 출력예제 1
YES
▣ 입력예제 2
abaCC
Caaab
▣ 출력예제 2
NO
 */
public class 아나그램 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input1 = sc.next();
		String input2 = sc.next();
		sc.close();
		
		Map<Character, Integer> counter1 = counter(input1);
		Map<Character, Integer> counter2 = counter(input2);
		
		String result = "YES";
		
		Set<Entry<Character, Integer>> set1 = counter1.entrySet();
		Set<Entry<Character, Integer>> set2 = counter2.entrySet();
		
		if(!set1.containsAll(set2)) {
			result = "NO";
		}
		
//		for(Entry<Character, Integer> en : counter1.entrySet()) {
//			if(!counter2.containsKey(en.getKey())) {
//				result = "NO";
//				break;
//			}
//			if(!counter2.get(en.getKey()).equals(en.getValue())) {
//				result = "NO";
//				break;
//			}
//		}
		System.out.println(result);
	}
	
	static Map<Character, Integer> counter(String input){
		Map<Character, Integer> counter = new HashMap<>();
		for(char cha : input.toCharArray()) {
			counter.compute(cha, (k, v) -> v == null ? 0 : v + 1);
		}
		return counter;
	}
}
