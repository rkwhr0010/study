package javabasic.lang.object;

import java.util.Objects;

public class Equals {
    public static void main(String[] args) {
        Value v1 = new Value(123);
        Value v2 = new Value(123);
        System.out.println(v1 == v2 ? "같아요" : "달라요");
        System.out.println(Objects.equals(v1, v2) ? "같아요" : "달라요");

    }
    static class Value {
        int value;
        public Value(int value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            if(Objects.isNull(obj)) return false;
            if(!(obj instanceof Value)) return false;
            Value other = (Value)obj;
            return this.value == other.value;
        }
    }
}
