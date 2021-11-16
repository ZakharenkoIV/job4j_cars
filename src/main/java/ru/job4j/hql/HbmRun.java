package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hql.model.Candidate;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {

            session.save(Candidate.of("Igor", 5, 5000));
            session.save(Candidate.of("Oleg", 2, 2000));
            session.save(Candidate.of("Ivan", 4, 4000));

            List<Candidate> allCandidates = session.createQuery("from Candidate").getResultList();

            Candidate candidateById = (Candidate) session.createQuery(
                    "from Candidate c where c.id = :id")
                    .setParameter("id", 2).uniqueResult();

            List<Candidate> candidatesByName = session.createQuery(
                    "from Candidate c where c.name = :name")
                    .setParameter("name", "Oleg").getResultList();

            session.createQuery("update Candidate c set c.salary = :salary where c.id = :id")
                    .setParameter("salary", 2500)
                    .setParameter("id", 2)
                    .executeUpdate();

            session.createQuery("delete from Candidate c where c.id = :id")
                    .setParameter("id", 1)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
