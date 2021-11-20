package ru.job4j.cars.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ad_photos")
public class AdPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "path", unique = true)
    @NotNull
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    @NotNull
    private Ad ad;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Ad getAd() {
        return ad;
    }

    private void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdPhoto adPhoto = (AdPhoto) o;
        return id == adPhoto.id && Objects.equals(path, adPhoto.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "AdPhoto{" + "id=" + id + ", path='" + path + ", ad=" + ad.getDescription() + '}';
    }
}
