package ru.job4j.cars.model;

import com.sun.istack.NotNull;
import ru.job4j.cars.dao.UserRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class Ad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String price;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @NotNull
    private String mileage;

    @NotNull
    private String color;

    @NotNull
    private String description;

    @Column(name = "brand_car")
    @NotNull
    private String brandCar;

    @Column(name = "body_type")
    @NotNull
    private String bodyType;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Column(name = "on_sale", columnDefinition = "BOOLEAN DEFAULT false")
    @NotNull
    private boolean onSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdPhoto> photos = new ArrayList<>();

    public Ad() {
    }

    public static Ad of(int id) {
        Ad ad = new Ad();
        ad.setId(id);
        return ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public boolean isSale() {
        return onSale;
    }

    public void setSale(boolean onSale) {
        this.onSale = onSale;
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

    public void setPhotos(List<AdPhoto> photos) {
        this.photos = photos;
    }

    public void addPhoto(AdPhoto photo) {
        photo.setAd(this);
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
        return id == ad.id && Objects.equals(created.getTime(), ad.created.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }

    @Override
    public String toString() {
        return "Ad{" + "id=" + id + ", description='" + description
                + ", done=" + onSale + '}';
    }

    public static class Builder {
        private Ad newAd;

        public Builder() {
            newAd = new Ad();
        }

        public Builder withDescription(String description) {
            newAd.setDescription(description);
            return this;
        }

        public Builder withPrice(String price) {
            newAd.setPrice(price);
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            newAd.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder withMileage(String mileage) {
            newAd.setMileage(mileage);
            return this;
        }

        public Builder withColor(String color) {
            newAd.setColor(color);
            return this;
        }

        public Builder withBrandCar(String brandCar) {
            newAd.setBrandCar(brandCar);
            return this;
        }

        public Builder withBodyType(String bodyType) {
            newAd.setBodyType(bodyType);
            return this;
        }

        public Builder withCreated(Date created) {
            newAd.setCreated(created);
            return this;
        }

        public Builder withOnSale(Boolean onSale) {
            newAd.setSale(onSale);
            return this;
        }

        public Builder withUserId(int userId) {
            newAd.setUser(UserRepository.getInstance().getUserById(userId));
            return this;
        }

        public Ad build() {
            return newAd;
        }
    }
}