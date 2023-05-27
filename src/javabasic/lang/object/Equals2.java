package javabasic.lang.object;

import java.util.Objects;

public class Equals2 {
    public static void main(String[] args) {
        Person p1 = new Person(123L, "아무개");
        Person p2 = new Person(123L, "개명함");
        System.out.println(p1 == p2 ? "같아요" : "달라요");
        System.out.println(Objects.equals(p1, p2) ? "같아요" : "달라요");

    }
    static class Person {
    	//id 식별값으로만 동치성을 판단, 이런 식으로 활용도 가능
        Long id;
        String name;
        public Person(Long value, String name) {
            this.id = value;
        }
        @Override
        public boolean equals(Object obj) {
            if(Objects.isNull(obj)) return false;
            if(!(obj instanceof Person)) return false;
            Person other = (Person)obj;
            return this.id == other.id;
        }
    }
}
