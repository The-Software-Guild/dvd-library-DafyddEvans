package com.m3.c130.dvd_library;

import java.util.List;
import java.util.Map;

public interface DVDLibDao {

    boolean addDVD(DVD dvd);

    boolean removeDVD(DVD dvd);

    List<DVD> getDVDList();

    boolean editDVD(int dvdSelection, int selection, String change);

    boolean loadDVD(String fileName);

    boolean saveDVD(String fileName);

    boolean deleteDVDLib(String fileName);

    List<String> listFiles();

    Map<String, DVD> getDVDTitleMap();
}
