package com.example.tgbot;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);
        System.out.println("done");
    }
}
