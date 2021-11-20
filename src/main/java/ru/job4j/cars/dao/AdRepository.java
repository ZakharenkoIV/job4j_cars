package ru.job4j.cars.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Ad;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Ad> getAdsForLastDay() {
        String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return transactionWrapper(session -> session.createQuery(
                "from Ad a where to_char(a.created,'yyyy-MM-dd') = :created", Ad.class
        ).setParameter("created", lastDay).getResultList());
    }

    public List<Ad> getAdsWithPhoto() {
        return transactionWrapper(session -> session.createQuery(
                "select distinct a from Ad a "
                        + "left join a.photos p where p != :empty", Ad.class
        ).setParameter("empty", Collections.EMPTY_LIST).getResultList());
    }

    public List<Ad> getAdsSpecificBrand(String brand) {
        return transactionWrapper(session -> session.createQuery(
                "from Ad a where a.brandCar = :brand", Ad.class
        ).setParameter("brand", brand).getResultList());
    }

    <T> T transactionWrapper(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try (session) {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public static AdRepository getInstance() {
        return SingleAdRepository.INSTANCE;
    }

    private static class SingleAdRepository {
        private static final AdRepository INSTANCE = new AdRepository();
    }
}
