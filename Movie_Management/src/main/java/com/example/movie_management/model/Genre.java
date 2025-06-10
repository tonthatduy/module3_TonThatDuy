package com.example.movie_management.model;

public class Genre {
    private int idGenre;
    private String nameGenre;
    private String descriptionGenre;

    public Genre() {
    }

    public Genre(int idGenre, String nameGenre, String descriptionGenre) {
        this.idGenre = idGenre;
        this.nameGenre = nameGenre;
        this.descriptionGenre = descriptionGenre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
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
