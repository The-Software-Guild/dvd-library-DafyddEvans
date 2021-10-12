package com.m3.c130.dvd_library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DVD {

    private final SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

    private String title;
    private Date releaseDate;
    private String mPAA;
    private String director;
    private String studio;
    private String userRating;

    public DVD(String title, String releaseDate, String mPAA, String director, String studio, String userRating) throws ParseException {
        this.title = emptyCheck(title);
        this.releaseDate = ft.parse(dateCheck(releaseDate));
        this.mPAA = emptyCheck(mPAA);
        this.director = emptyCheck(director);
        this.studio = emptyCheck(studio);
        this.userRating = emptyCheck(userRating);
    }

    public DVD(String[] arr) throws ParseException {
        this(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
    }

    private String emptyCheck(String str) {
        return (!str.equals("") ? str : " ");
    }

    private String dateCheck(String releaseDate) {
        if (releaseDate.equals("") || releaseDate.equals(" ")) {
            return "01/01/1900";
        } else {
            return releaseDate;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return ft.format(releaseDate);
    }

    public void setReleaseDate(String releaseDate) throws ParseException {
        this.releaseDate = ft.parse(dateCheck(releaseDate));
    }

    public String getmPAA() {
        return mPAA;
    }

    public void setmPAA(String mPAA) {
        this.mPAA = mPAA;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%nRelease Date: %s%nMPAA Rating: " +
                        "%s%nDirector: %s%nStudio: %s%nUser Rating: %s%n",
                title, getReleaseDate(), mPAA, director, studio, userRating);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DVD)) {
            return false;
        }
        DVD dvd = (DVD) obj;
        return title.equals(dvd.title) &&
                releaseDate.equals(dvd.releaseDate) &&
                mPAA.equals(dvd.mPAA) &&
                director.equals(dvd.director) &&
                studio.equals(dvd.studio) &&
                userRating.equals(dvd.userRating);
    }

}


