package com.mtb.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name = "GENRE_ID", nullable = false)
    private String genreId;

    @Column(name = "NAME", nullable = false)
    private String genreName;

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Genre(String genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Genre() {
    }
}
