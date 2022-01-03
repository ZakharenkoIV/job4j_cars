package ru.job4j.cars.model;

import com.sun.istack.NotNull;
import ru.job4j.cars.dao.AdRepository;

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
    @JoinColumn(name = "ad_id")
    @NotNull
    private Ad ad;

    public AdPhoto() {
    }

    public AdPhoto(int id) {
        this.id = id;
    }

    public AdPhoto(String path) {
        this.path = path;
    }

    public static AdPhoto of(String path) {
        AdPhoto adPhoto = new AdPhoto();
        adPhoto.setPath(path);
        return adPhoto;
    }

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

    public void setAd(Ad ad) {
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
        return "AdPhoto{" + "id=" + id + ", path='" + path + '}';
    }
}
