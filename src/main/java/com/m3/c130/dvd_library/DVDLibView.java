package com.m3.c130.dvd_library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DVDLibView {

    private final UserIO io;
    private final String[] iVariables = {"Title", "Release Date (dd/mm/yyyy)",
            "MPAA", "Director", "Studio", "User Rating"};

    public DVDLibView(UserIO io) {
        this.io = io;
    }

    public int menuSelection() {
        io.print("--------------------");
        io.print("------- MENU -------");
        io.print("--------------------");
        io.print("1. Display Library");
        io.print("2. Display dvd in Library");
        io.print("3. Edit dvd in Library");
        io.print("4. Add to Library");
        io.print("5. Remove from Library");
        io.print("6. Save Library to memory");
        io.print("7. Load Library from memory");
        io.print("8. Remove Library from memory");
        io.print("9. Search Library for Dvd by title");
        io.print("0. Exit");
        return io.readInt("Enter an integer between 0 - 9:", 0, 9);

    }

    public void displayLibContentBanner() {
        io.print("---------------------");
        io.print("-- Display Library --");
        io.print("---------------------");
    }

    public void displayLibContent(List<DVD> lst) {
        io.print("List of DVDs in current library:");
        io.printList(lst);
    }

    public void displayEntryBanner() {
        io.print("----------------------------");
        io.print("-- Display Dvd in Library --");
        io.print("----------------------------");
    }

    public void displayDVD(DVD dvd) {
        io.print(dvd.fullDesc());
    }

    public void displayEditDVDBanner() {
        io.print("-------------------------");
        io.print("-- Edit Dvd in Library --");
        io.print("-------------------------");
    }

    public int getEditDVDSelection() {
        List<String> lst = Arrays.asList(iVariables);
        io.printList(lst);
        return getSelectionFromList(lst);
    }

    public String getDVDEdit(int selection) {
        return io.readString("What would you like to change " + iVariables[selection - 1] + " to? : ");
    }

    public void editSuccess(boolean bool) {
        if (bool) {
            io.print("Dvd successfully changed");
        } else {
            io.print("Something went wrong, please try again");
        }
    }

    public void displayDVDRemoveBanner() {
        io.print("-------------------------");
        io.print("-- Remove from Library --");
        io.print("-------------------------");
    }

    public void removeSuccess(boolean bool) {
        if (bool) {
            io.print("DVD successfully removed from library");
        } else {
            io.print("Couldn't remove dvd from library, please try again");
        }
    }

    public void displayAddDVDBanner() {
        io.print("------------------------");
        io.print("-- Add Dvd to Library --");
        io.print("------------------------");
    }

    public String[] getDVDInput() {
        String[] returnArr = new String[iVariables.length];
        io.print("Build your Dvd:");
        for (int i = 0; i < iVariables.length; i++) {
            returnArr[i] = io.readString((i + 1) + ". " + iVariables[i] + ": ");
        }
        return returnArr;
    }

    public void containsDVDFailure() {
        io.print("Library already contains this dvd");
    }

    public void addDvdSuccess(boolean bool) {
        if (bool) {
            io.print("DVD successfully added to library");
        } else {
            io.print("Input was incorrectly formatted, please try again");
        }
    }

    public void displaySaveBanner() {
        io.print("------------------");
        io.print("-- Save Library --");
        io.print("------------------");
    }

    public String getFileName() {
        return io.readString("Please enter a file name: ");
    }

    public void displayLoadBanner() {
        io.print("------------------");
        io.print("-- Load Library --");
        io.print("------------------");
    }

    public void displayLibList(List<?> lst) {
        io.print("List of available libraries:");
        io.printList(lst);
    }

    public void loadSuccess(boolean bool) {
        if (bool) {
            io.print("Library successfully loaded.");
        } else {
            io.print("Library couldn't be loaded, please try again.");
        }
    }

    public void libEmpty() {
        io.print("This library is empty");
    }

    public void noLibFound() {
        io.print("No libraries found.");
    }

    public void displayDeleteLibBanner() {
        io.print("--------------------");
        io.print("-- Delete Library --");
        io.print("--------------------");
    }

    public void libDeleteSuccess(boolean bool) {
        if (bool) {
            io.print("Library successfully deleted");
        } else {
            io.print("Unable to delete library, please try again later");
        }
    }

    public void displayDVDSearchBanner() {
        io.print("--------------------");
        io.print("-- Search Library --");
        io.print("--------------------");
    }

    public String getQuery() {
        return io.readString("Please enter the Title of the dvd you are looking for:");
    }

    public void queryUnsuccessful() {
        io.print("Library does not contain query");
    }

    public void waitForKey() {
        io.readString("Press any key to continue..");
    }

    public void exitMessage() {
        io.print("Application closing, Goodbye.");
    }

    public int getSelectionFromList(List<?> lst) {
        io.print("0. Back to main menu");
        String prompt = "Make a selection from the list (0" + (lst.size() > 0 ? " - " + lst.size() : "") + "): ";
        return io.readInt(prompt, 0, lst.size());
    }
}
