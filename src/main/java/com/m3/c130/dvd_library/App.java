package com.m3.c130.dvd_library;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOImpl();
        DVDLibView view = new DVDLibView(io);
        DVDLibDao dao = new DVDDaoFileImpl();
        DVDLibController controller = new DVDLibController(view, dao);
        controller.run();
    }
}
