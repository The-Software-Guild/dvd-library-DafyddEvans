package com.m3.c130.dvd_library;


import java.util.List;

public interface UserIO {

    void print(Object msg);

    void printList(List<?> lst);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
}
