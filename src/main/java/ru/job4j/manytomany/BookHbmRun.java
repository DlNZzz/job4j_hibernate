package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookHbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Book one = Book.of("bookOne");
            Book two = Book.of("bookTwo");

            Author authorOne = Author.of("authorOne");
            Author authorTwo = Author.of("authorTwo");

            one.getAuthors().add(authorOne);
            one.getAuthors().add(authorTwo);

            two.getAuthors().add(authorOne);

            //session.persist(one);
            //session.persist(two);

            Book book = session.get(Book.class, 2);
            session.remove(book);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
