package javabasic.lang.object;

import java.util.Arrays;

public class Clone03 {
    public static void main(String[] args){
    	int[] arr = {1,2,3};
    	int[] arr2 = arr.clone();
    	arr2[0]=4;
    	arr2[1]=5;
    	arr2[2]=6;
    	System.out.println(Arrays.toString(arr));
    	System.out.println(Arrays.toString(arr2));
    }
}
