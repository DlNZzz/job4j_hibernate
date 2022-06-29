package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<Category> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
/*
            Category category = Category.of("Consulting");
            session.save(category);
            Task taskOne = Task.of("Hibernate", category);
            Task taskTwo = Task.of("Spring", category);
            Task taskThree = Task.of("Servlet", category);

            session.save(taskOne);
            session.save(taskTwo);
            session.save(taskThree);
 */

            list = session.createQuery("from Category").list();

            for (Category c : list) {
                for (Task task : c.getTasks()) {
                    System.out.println(task);
                }
            }

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}