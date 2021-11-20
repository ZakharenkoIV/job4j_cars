package ru.job4j.cars.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "brand_car")
    @NotNull
    private String brandCar;

    @Column(name = "body_type")
    @NotNull
    private String bodyType;

    @Column(name = "created")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());

    @Column(name = "done", columnDefinition = "false")
    @NotNull
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdPhoto> photos = new ArrayList<>();

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Date getCreated() {
        return created;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public List<AdPhoto> getPhotos() {
        return photos;
    }

    private void setPhotos(List<AdPhoto> photos) {
        this.photos = photos;
    }

    public void addPhoto(AdPhoto photo) {
        photos.add(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id && Objects.equals(created, ad.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }

    @Override
    public String toString() {
        return "Ad{" + "id=" + id + ", description='" + description
                + ", done=" + done + '}';
    }

}