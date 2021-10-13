package com.m3.c130.dvd_library;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class DVDDaoFileImpl implements DVDLibDao {

    private final String path = "save_files/";
    private final File curDir = new File("./" + path);
    private final String DELIMITER = "::";
    private List<DVD> dvds = new ArrayList<>();

    @Override
    public boolean addDVD(DVD dvd) {
        if (!dvds.contains(dvd)) {
            dvds.add(dvd);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeDVD(DVD dvd) {
        return dvds.remove(dvd);
    }

    @Override
    public List<DVD> getDVDList() {
        return dvds;
    }

    @Override
    public boolean editDVD(int dvdSelection, int selection, String change) {
        DVD dvd = dvds.get(dvdSelection);
        switch (selection) {
            case 1:
                try {
                    dvd.setReleaseDate(change);
                    break;
                } catch (ParseException e) {
                    return false;
                }
            case 2:
                dvd.setmPAA(change);
                break;
            case 3:
                dvd.setDirector(change);
                break;
            case 4:
                dvd.setStudio(change);
                break;
            case 5:
                dvd.setUserRating(change);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean loadDVD(String fileName) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(path + fileName + ".txt")));
            dvds.clear();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                dvds.add(new DVD(currentLine.split(DELIMITER)));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveDVD(String fileName) {
        fileName = fileName.split("\\.")[0];
        try {
            File file = new File(path + fileName + ".txt");
            FileWriter writer = new FileWriter(file);
            file.createNewFile();
            for (DVD item : dvds) {
                writer.write(item.getTitle() + DELIMITER + item.getReleaseDate() + DELIMITER +
                        item.getmPAA() + DELIMITER + item.getDirector() + DELIMITER +
                        item.getStudio() + DELIMITER + item.getUserRating() + DELIMITER);
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean deleteDVDLib(String fileName) {
        try {
            File file = new File(path + fileName + ".txt");
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> listFiles() {
        File[] filesList = curDir.listFiles();
        List<String> textFiles = new ArrayList<>();
        for (File f : filesList) {
            if (f.isFile()) {
                String[] fileType = f.getName().split("\\.");
                if (fileType[1].equals("txt")) {
                    textFiles.add(fileType[0]);
                }
            }
        }
        return textFiles;
    }

    @Override
    public Map<String, DVD> getDVDTitleMap() {
        Map<String, DVD> map = new HashMap<>();
        for (DVD dvd : dvds) {
            map.put(dvd.getTitle(), dvd);
        }
        return map;
    }
}
