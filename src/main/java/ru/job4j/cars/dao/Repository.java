package ru.job4j.cars.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Function;

public class Repository {
    private final SessionFactory sf = Repository.getInstanceSF();

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

    public static SessionFactory getInstanceSF() {
        return Repository.SingleSessionFactory.INSTANCE;
    }

    private static class SingleSessionFactory {
        private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
                .configure().build();
        private static final SessionFactory INSTANCE = new MetadataSources(REGISTRY)
                .buildMetadata().buildSessionFactory();
    }
}
