package com.m3.c130.dvd_library;

import java.util.List;
import java.util.Scanner;

public class UserIOImpl implements UserIO {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void print(Object msg) {
        System.out.println(msg);
    }

    @Override
    public void printList(List<?> lst) {
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1 + ": " + lst.get(i)));
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        int input;
        while (true) {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a integer");
            }
        }
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int input = readInt(prompt);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Input out of range, please enter a valid input.");
            }
        }
    }
}
