package ru.job4j.cars.dao;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdPhoto;
import ru.job4j.cars.model.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;

public class AdRepositoryTest {
    Ad ad1, ad2, adLastDay, adNotSave;

    @Before
    public void loadTablesData() {

        UserRepository.getInstance().save(
                User.of("userTest", "q@q.ru", "0", 1));

        ad1 = new Ad.Builder()
                .withPrice("300999")
                .withPhoneNumber("11-111-111")
                .withMileage("10598")
                .withColor("Чёрный")
                .withDescription("Первая машина")
                .withBrandCar("BMW")
                .withBodyType("Сидан")
                .withCreated(new Date(51516516L))
                .withOnSale(true)
                .withUserId(1)
                .build();
        AdRepository.getInstance().save(ad1);

        ad2 = new Ad.Builder()
                .withPrice("100555")
                .withPhoneNumber("55-555-555")
                .withMileage("90755")
                .withColor("белий")
                .withDescription("Вторая машина")
                .withBrandCar("Audi")
                .withBodyType("Сидан")
                .withCreated(new Date(77722216L))
                .withOnSale(true)
                .withUserId(1)
                .build();
        AdPhoto adPhoto = AdPhoto.of("/dd/qqq/fd.png");
        AdPhotoRepository.getInstance().save(adPhoto);
        ad2.addPhoto(adPhoto);
        AdRepository.getInstance().save(ad2);

        adLastDay = new Ad.Builder()
                .withPrice("777000")
                .withPhoneNumber("72-720-772")
                .withMileage("2755")
                .withColor("синий")
                .withDescription("Третья машина")
                .withBrandCar("Mercedes")
                .withBodyType("Сидан")
                .withCreated(new Date())
                .withOnSale(true)
                .withUserId(1)
                .build();
        AdRepository.getInstance().save(adLastDay);

        adNotSave = new Ad.Builder()
                .withPrice("10000")
                .withPhoneNumber("200-123-123")
                .withMileage("85055")
                .withColor("красный")
                .withDescription("Четвёртая машина")
                .withBrandCar("Mercedes")
                .withBodyType("Сидан")
                .withCreated(new Date(999888L))
                .withOnSale(false)
                .withUserId(1)
                .build();
    }

    @After
    public void truncateTable() {
        AdPhotoRepository.getInstance().truncateTable();
        AdRepository.getInstance().truncateTable();
        UserRepository.getInstance().truncateTable();
    }

    @Test
    public void whenGetAdById() {
        Ad expected = ad1;
        Ad actual = AdRepository.getInstance().getAdById(ad1.getId());
        assertThat(actual, Is.is(expected));
    }

    @Test
    public void whenGetAllAds() {
        List<Ad> expected = List.of(ad1, ad2, adLastDay);
        List<Ad> actual = AdRepository.getInstance().getAllAds();
        assertThat(actual, Is.is(expected));
    }

    @Test
    public void whenSaveAd() {
        AdRepository.getInstance().save(adNotSave);
        Ad expected = adNotSave;
        Ad actual = AdRepository.getInstance().getAdById(adNotSave.getId());
        assertThat(actual, Is.is(expected));
    }

    @Test
    public void whenGetAdsForLastDay() throws ParseException {
        Ad expected = adLastDay;
        Ad actual = AdRepository.getInstance().getAdsForLastDay().get(0);
        assertThat(actual, Is.is(expected));
    }

    @Test
    public void whenGetAdsWithPhoto() {
        Ad expected = ad2;
        Ad actual = AdRepository.getInstance().getAdsWithPhoto().get(0);
        assertThat(actual, Is.is(expected));
    }

    @Test
    public void whenGetAdsSpecificBrand() {
        Ad expected = ad1;
        Ad actual = AdRepository.getInstance().getAdsSpecificBrand("BMW").get(0);
        assertThat(actual, Is.is(expected));
    }
}