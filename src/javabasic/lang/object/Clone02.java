package javabasic.lang.object;

import java.util.ArrayList;

public class Clone02 {
    public static void main(String[] args){
    	ArrayList<Object> list = new ArrayList<>();
    	list.add("문자");
    	list.add(10000000L);
    	list.add(0.0000001);
    	//저장된 요소가 Cloneable구현하고 있으면 OK
    	ArrayList<Object> list2 = (ArrayList<Object>) list.clone();
    }
}
