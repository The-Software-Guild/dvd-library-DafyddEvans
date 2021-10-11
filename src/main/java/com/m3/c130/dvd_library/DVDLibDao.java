package com.m3.c130.dvd_library;

import java.util.List;

public interface DVDLibDao {

    boolean addDVD(DVD dvd);

    boolean removeDVD(DVD dvd);

    List<DVD> getDVDList();

    DVD findDVD(String name);

    boolean editDVD(DVD dvd);

    boolean loadDVD(String fileName);

    boolean saveDVD(String fileName);

    boolean deleteDVDLib(String fileName);

    List<String> listFiles();
}
