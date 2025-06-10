package com.example.movie_management.dto;

public class MovieDtoResponse {
    private int idMovie;
    private String title;
    private String director;
    private int releaseYear;
    private int duration;
    private String language;
    private String description;
    private String nameGenre;
    private String descriptionGenre;

    public MovieDtoResponse() {
    }

    public MovieDtoResponse(int idMovie, String title, String director, int releaseYear, int duration, String language, String description, String nameGenre, String descriptionGenre) {
        this.idMovie = idMovie;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.language = language;
        this.description = description;
        this.nameGenre = nameGenre;
        this.descriptionGenre = descriptionGenre;
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

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getDescriptionGenre() {
        return descriptionGenre;
    }

    public void setDescriptionGenre(String descriptionGenre) {
        this.descriptionGenre = descriptionGenre;
    }
}
