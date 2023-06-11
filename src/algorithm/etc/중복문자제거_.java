package algorithm.etc;
import java.util.Arrays;

public class 중복문자제거_ {
    public static void main(String[] args) {
        String value = "112233112ㅁㄴㅇㅁㄴㅇ2334551123";
        String answer = "";
        
        int len = value.length();
        for(int i = 0 ; i<len ;i++) {
            //indexOf 는 가장 앞에 존재하는 문자를 반환한다.
            //charAt 은 특정 위치 char를 반환한다. 
            //따라서 아래 조건문은 첫 번째 존재하는 문자와 인덱스 위치만 참이된다.
            if(value.indexOf( value.charAt(i)) == i )  {
                answer += value.charAt(i);
            }
        }
        System.out.println(answer);
        
        Character[] array = value.chars()
                                 .distinct()
                                 .mapToObj(num->((char)num))
                                 .toArray(Character[]::new);
        System.out.println(Arrays.toString(array));
        
    }
}
