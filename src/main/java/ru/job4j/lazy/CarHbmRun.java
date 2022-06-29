package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class CarHbmRun {
    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
/*
            Brand brand = Brand.of("kia");
            session.save(brand);
            Model modelOne = Model.of("rio", brand);
            Model modelTwo = Model.of("k5", brand);
            session.save(modelOne);
            session.save(modelTwo);

 */

            list = session.createQuery(
                    "select distinct b from Brand b join fetch b.models"
            ).list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        for (Model model : list.get(0).getModels()) {
            System.out.println(model);
        }

    }
}