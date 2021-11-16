package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hql.model.Candidate;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {

            Candidate candidate = session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.vacancyBase vb "
                            + "join fetch vb.vacancies v "
                            + "where c.id = :id", Candidate.class
            ).setParameter("id", 2).uniqueResult();

            System.out.println(candidate.toString());
            System.out.println(candidate.getVacancyBase().toString());
            System.out.println(candidate.getVacancyBase().getVacancies().toString());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
