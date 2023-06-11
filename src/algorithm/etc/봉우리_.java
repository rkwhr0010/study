package algorithm.etc;


import java.util.*;
import java.util.stream.IntStream;
/*
핵심
별도 탐색 배열을 만드는 것
탐색 배열을 이용해 반복문으로 인덱스 보정
보정 값에 조건문
탐색 배열의 이점, 조건문 4번으로 검사해도 되지만, 
만약 좌상,상, 우상, 이렇게 8방향을 검사하라고 하면?
*/
class 봉우리_ { 
    public static void main(String[] args){
        //봉우리 크기
        final int LENGTH = 10;
        //봉우리 높이
        final int HEIGTH = 10;
        int answer=0;
        int[][] arr =   IntStream.range(0, LENGTH)
                                 .mapToObj(num->{
                                     return new Random().ints(LENGTH, 0, HEIGTH)
                                                        .toArray();
                                 }).toArray(int[][]::new);
        prettyPrint(arr);
        
        // 타겟[i][j] 위치에 상 우 하 좌 위치 탐색 배열
        int[] dx={-1, 0, 1, 0};
        int[] dy={0, 1, 0, -1};
        
        for(int i=0; i<LENGTH; i++){
            for(int j=0; j<LENGTH; j++){
                
                boolean flag=true;
                //탐색을 위한 반복문 순회
                for(int k=0; k<4; k++){
                    //인덱스 보정   상, 우, 하, 좌
                    int ni=i+dx[k];
                    int nj=j+dy[k];
                    // 보정인덱스는 -1이나 배열크기면 크다고 가정한다. 
                    // 예를들어 [0,0] [마지막][마지막] 배열을 보정하면 배열범위를 초과해 익셉션 발생
                    if(0<=ni && ni<LENGTH && 0 <= nj && nj<LENGTH && arr[i][j]<=arr[ni][nj] ){
                        flag=false;
                        //한 개라도 작거나 같으면 break
                        break;
                    }
                }
                if(flag) {
                    System.out.print("["+i+","+j+"] "+arr[i][j]+(answer%5==4?"\n":""));
                    answer++;
                }
            }
        }
        System.out.println();
        System.out.println(answer);
    }
    static void prettyPrint(int[][] arr, boolean isIndex) {
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr[i].length;j++) {
                if(isIndex) System.out.printf("%2d,%2d ", i,j);
                else System.out.printf("%2d" + (j==arr[i].length-1? "":",") ,arr[i][j]);
            }
            System.out.println(isIndex?"\n":"");
        }
    }
    static void prettyPrint(int[][] arr) {
        prettyPrint(arr, false);
    }
}