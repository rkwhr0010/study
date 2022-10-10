
import java.util.HashMap;
import java.util.Random;
class 모든아나그램찾기_ {	
	public static void main(String[] args){
		String a = init(300); // 검사 대상
		String b = init(3); // 어나그램
		
		int answer=0;
		HashMap<Character, Integer> inputMap = new HashMap<Character, Integer>();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		//초기화
		for(int i=0;i<b.length()-1;i++) {
			char tmp1 = a.charAt(i);
			char tmp2 = b.charAt(i);
			inputMap.put(tmp1, inputMap.getOrDefault(tmp1, 0)+1 );
			map.put(tmp2, map.getOrDefault(tmp2, 0)+1 );
		}
		map.put(b.charAt(b.length()-1), map.getOrDefault(b.charAt(b.length()-1), 0)+1 );
		
		for(int lt=0, rt=b.length()-1 ;rt<a.length();rt++) {
			char tmp = a.charAt(rt);
			//1. 추가
			inputMap.put(tmp, inputMap.getOrDefault(tmp, 0)+1);
			
			//2. 검증 
			/* 메서드 안쓰고 할 시
			boolean flag= true;
			for(Character cha : map.keySet()) {
				//키 검증
				if(!inputMap.containsKey(cha)) {
					flag=false;
					break;
				}
				//개수 검증
				if(!(inputMap.get(cha) == map.get(cha))) {
					flag=false;
					break;
				}
				
			}
			if(flag) {
				System.out.println("일치 : " + lt +", " +rt);
				answer++;
			}
			*/
			if(inputMap.equals(map)) {
				System.out.println("일치 : " + lt +", " +rt);
				answer++;
			}
			
			//3. 삭제&수정&추가
			tmp = a.charAt(lt);
			inputMap.put(tmp, inputMap.get(tmp)-1 );
			
			if(inputMap.get(tmp)==0 ) {
				inputMap.remove(tmp);
			}
			lt++;
		}
		int count = 1;
		
		for(int i=0; i<a.length();i++) {
			if(count==1) System.out.printf("%3s %s\n",0,a.substring(i, i+10));
			if(count%10==0) {
				if( i+10-1<a.length() ) {
					System.out.printf("%3s %s" , ""+count,a.substring(i+1, i+1+10) );
					System.out.println();
				}
			}
			count++;
			
		}
		System.out.println("탐색문자 : "+b);
		System.out.println("갯수 : " +answer);
		
	}

	static String init(int size) {
		int[] array = new Random().ints(size, 65, 70).map(n -> {
			if ((int) (Math.random() * 10) % 2 == 0) {
				n += 32;
			}
			return n;
		}).toArray();
		StringBuilder sb = new StringBuilder();
		for (int num : array) {
			sb.append((char) num);
		}
		return sb.toString();
	}
}