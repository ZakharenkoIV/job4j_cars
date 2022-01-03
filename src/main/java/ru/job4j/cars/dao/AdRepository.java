package ru.job4j.cars.dao;

import ru.job4j.cars.model.Ad;
import ru.job4j.cars.model.AdPhoto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdRepository extends Repository {

    private AdRepository() {
    }

    public static AdRepository getInstance() {
        return SingleAdRepository.INSTANCE;
    }

    private static class SingleAdRepository {
        private static final AdRepository INSTANCE = new AdRepository();
    }

    public List<Ad> getAllAds() {
        return transactionWrapper(session -> session.createQuery(
                "SELECT a FROM Ad a left join fetch a.user u left join fetch a.photos p",
                Ad.class)
                .getResultList());
    }

    public List<Ad> getMyAds(int userId) {
        return transactionWrapper(session -> session.createQuery(
                "SELECT a FROM Ad a left join fetch a.user u left join fetch a.photos p"
                        + " WHERE u.id =:userId", Ad.class)
                .setParameter("userId", userId)
                .getResultList());
    }

    public void deleteAd(int adId) {
        Ad ad = getAdById(adId);
        for (AdPhoto photo : ad.getPhotos()) {
            AdPhotoRepository.getInstance().delete(photo);
        }
        ad.setPhotos(new ArrayList<>());
        transactionWrapper(session -> {
            session.delete(ad);
            return ad;
        });
    }

    public List<Ad> getAdsForLastDay() throws ParseException {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(formatWithoutTime.parse(formatWithoutTime.format(date)));
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        return transactionWrapper(session -> session.createQuery(
                "FROM Ad a WHERE a.created <= :created and a.created > :yesterday", Ad.class)
                .setParameter("created", date)
                .setParameter("yesterday", yesterday)
                .getResultList());
    }

    public List<Ad> getAdsWithPhoto() {
        return transactionWrapper(session -> session.createQuery(
                "select distinct a from Ad a inner join fetch a.photos p", Ad.class
        ).getResultList());
    }

    public List<Ad> getAdsSpecificBrand(String brand) {
        return transactionWrapper(session -> session.createQuery(
                "from Ad a where a.brandCar = :brand", Ad.class
        ).setParameter("brand", brand).getResultList());
    }

    public int save(Ad ad) {
        return transactionWrapper(session -> {
            int identifier = (int) session.save(ad);
            for (AdPhoto photo : ad.getPhotos()) {
                photo.setAd(ad);
            }
            return identifier;
        });
    }

    public void update(Ad ad) {
        transactionWrapper(session -> {
            session.saveOrUpdate(ad);
            return 1;
        });
    }

    public Ad getAdById(int adId) {
        return transactionWrapper(session -> session.createQuery(
                "SELECT a FROM Ad a left join fetch a.user u left join fetch a.photos p"
                        + " WHERE a.id =:adId", Ad.class)
                .setParameter("adId", adId)
                .getSingleResult());
    }

    public void truncateTable() {
        transactionWrapper(session -> session.createSQLQuery(
                "truncate table ads RESTART IDENTITY").executeUpdate());
    }
}
