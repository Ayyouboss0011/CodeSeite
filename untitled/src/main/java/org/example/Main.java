package org.example;

public class Main {
    public static void main(String[] args){
        for( int i = 0 ; i <= 9999 ; i++ ){
            String formattedNumber = String.format("%04d", i);
            System.out.println(formattedNumber);
        }

    }
}