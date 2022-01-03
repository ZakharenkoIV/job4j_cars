package ru.job4j.cars.dao;

import ru.job4j.cars.model.AdPhoto;

public class AdPhotoRepository extends Repository {

    private AdPhotoRepository() {
    }

    public static AdPhotoRepository getInstance() {
        return AdPhotoRepository.SingleAdPhotoRepository.INSTANCE;
    }

    public void delete(AdPhoto photo) {
        transactionWrapper(session -> {
            session.delete(photo);
            return photo;
        });
    }

    private static class SingleAdPhotoRepository {
        private static final AdPhotoRepository INSTANCE = new AdPhotoRepository();
    }

    public int save(AdPhoto adPhoto) {
        return (int) transactionWrapper(session -> session.save(adPhoto));
    }

    public AdPhoto getAdPhotoById(int adPhotoId) {
        return transactionWrapper(session -> session.get(AdPhoto.class, adPhotoId));
    }

    public void truncateTable() {
        transactionWrapper(session -> session.
                createSQLQuery("truncate table ad_photos RESTART IDENTITY").executeUpdate());
    }
}
