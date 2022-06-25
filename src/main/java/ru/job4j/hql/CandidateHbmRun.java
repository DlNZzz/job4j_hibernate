package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class CandidateHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Candidate student = Candidate.of("Anton", "no", 1500);
            Candidate studentTwo = Candidate.of("Roman", "yes", 3000);
            Candidate studentThree = Candidate.of("Stepan", "yes", 2500);
            session.save(student);
            session.save(studentTwo);
            session.save(studentThree);
            Query query = session.createQuery("from Candidate");
            for (Object s : query.list()) {
                System.out.println(s);
            }
            Query query1 = session.createQuery("from Candidate c where c.id = 1");
            System.out.println(query1.uniqueResult());
            Query query2 = session.createQuery("update Candidate c set c.name = 'Nikolay', c.salary = :newSalary where c.id = 2");
            query2.setParameter("newSalary", 3500);
            query2.executeUpdate();
            session.createQuery("delete Candidate c where c.id = :deleteId")
                    .setParameter("deleteId", 2)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
