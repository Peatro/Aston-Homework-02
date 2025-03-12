package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            OtherClass.exceptionThrower();
        } catch (CustomException e) {
            System.out.println(e.getClass() + " " + e.getMessage());
        }
    }
}

