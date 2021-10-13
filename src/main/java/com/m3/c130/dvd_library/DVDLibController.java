package com.m3.c130.dvd_library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DVDLibController {

    private final DVDLibView view;
    private final DVDLibDao dao;

    public DVDLibController(DVDLibView view, DVDLibDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        int menuSelection;
        boolean running = true;
        while (running) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    displayLibrary();
                    break;
                case 2:
                    displayEntry();
                    break;
                case 3:
                    editDVD();
                    break;
                case 4:
                    addDVD();
                    break;
                case 5:
                    removeFromLibrary();
                    break;
                case 6:
                    saveLibrary();
                    break;
                case 7:
                    loadLibrary();
                    break;
                case 8:
                    deleteDVDLib();
                    break;
                case 9:
                    searchLib();
                    break;
                case 0:
                    running = exit();
                    break;
                default:
                    break;
            }
            if (running) {
                view.waitForKey();
            }
        }
    }

    private int getMenuSelection() {
        return view.menuSelection();
    }

    private void displayLibrary() {
        List<DVD> content = dao.getDVDList();
        view.displayLibContentBanner();
        if (content.size() > 0) {
            view.displayLibContent(content);
        } else {
            view.libEmpty();
        }
    }

    private int displayEntry() {
        List<DVD> content = dao.getDVDList();
        view.displayEntryBanner();
        if (content.size() > 0) {
            view.displayLibContent(content);
            int selection = view.getSelectionFromList(content);
            if (selection > 0) {
                view.displayDVD(content.get(selection - 1));
                return selection;
            }
        }
        return 0;
    }

    private void editDVD() {
        int dvdSelection = displayEntry();
        if (dvdSelection > 0) {
            view.displayEditDVDBanner();
            int selection = view.getEditDVDSelection();
            if (selection > 0) {
                String change = view.getDVDEdit(selection);
                view.editSuccess(dao.editDVD(dvdSelection - 1, selection, change));
            }
        }
    }

    private void addDVD() {
        List<DVD> dvds = dao.getDVDList();
        view.displayAddDVDBanner();
        try {
            String[] input = view.getDVDInput();
            DVD dvd = new DVD(input);
            if (!dvds.contains(dvd)) {
                dao.addDVD(dvd);
                view.addDvdSuccess(true);
            } else {
                view.containsDVDFailure();
            }
        } catch (Exception e) {
            view.addDvdSuccess(false);
        }
    }


    private void removeFromLibrary() {
        List<DVD> content = dao.getDVDList();
        view.displayDVDRemoveBanner();
        if (content.size() > 0) {
            view.displayLibContent(content);
            int selection = view.getSelectionFromList(content);
            if (selection > 0) {
                view.removeSuccess(dao.removeDVD(content.get(selection - 1)));
            }
        } else {
            view.libEmpty();
        }
    }

    private void saveLibrary() {
        view.displaySaveBanner();
        String fileName = view.getFileName();
        dao.saveDVD(fileName);
    }

    private void loadLibrary() {
        List<String> libList = dao.listFiles();
        view.displayLoadBanner();
        if (libList.size() > 0) {
            view.displayLibList(libList);
            int selection = view.getSelectionFromList(libList);
            if (selection > 0) {
                view.loadSuccess(dao.loadDVD(libList.get(selection - 1)));
            }
        } else {
            view.noLibFound();
        }
    }

    private void deleteDVDLib() {
        List<String> libList = dao.listFiles();
        view.displayDeleteLibBanner();
        if (libList.size() > 0) {
            view.displayLibList(libList);
            int selection = view.getSelectionFromList(libList);
            if (selection > 0) {
                view.libDeleteSuccess(dao.deleteDVDLib(libList.get(selection - 1)));
            }
        } else {
            view.noLibFound();
        }

    }

    private void searchLib() {
        Map<String, DVD> dvds = dao.getDVDTitleMap();
        List<String> dvdTitles = new ArrayList<>(dvds.keySet());
        view.displayDVDSearchBanner();
        String query = view.getQuery();
        if (dvdTitles.contains(query)) {
            view.displayDVD(dvds.get(query));
        } else {
            view.queryUnsuccessful();
        }
    }

    private boolean exit() {
        view.exitMessage();
        return false;
    }
}
