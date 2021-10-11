package com.m3.c130.dvd_library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DVDDaoFileImpl implements DVDLibDao {

    private final String path = "save_files/";
    private final File curDir = new File("./" + path);
    private final String regex = "::";
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
    public DVD findDVD(String name) {
        return null;
    }

    @Override
    public boolean editDVD(DVD dvd) {
        return false;
    }

    @Override
    public boolean loadDVD(String fileName) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(path + fileName + ".txt")));
            dvds.clear();
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                dvds.add(new DVD(currentLine.split(regex)));
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
                writer.write(item.getTitle() + regex + item.getReleaseDate() + regex +
                        item.getmPAA() + regex + item.getDirector() + regex +
                        item.getStudio() + regex + item.getUserRating() + regex);
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
}
