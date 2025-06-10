package com.example.movie_management.model;

public class Movie {
    private int idMovie;
    private String title;
    private String director;
    private int releaseYear;
    private int duration;
    private String language;
    private String description;
    private int idGenre;

    public Movie() {
    }

    public Movie(int idMovie, String title, String director, int releaseYear, int duration, String language, String description, int idGenre) {
        this.idMovie = idMovie;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.language = language;
        this.description = description;
        this.idGenre = idGenre;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
}
