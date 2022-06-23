package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class CarHbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Model model = Model.of("celica");
            session.save(model);
            model = Model.of("camry");
            session.save(model);
            model = Model.of("rav4");
            session.save(model);
            model = Model.of("supra");
            session.save(model);
            model = Model.of("crown");
            session.save(model);

            Brand brand = Brand.of("toyota");
            brand.addModel(session.load(Model.class, 1));
            brand.addModel(session.load(Model.class, 2));
            brand.addModel(session.load(Model.class, 3));
            brand.addModel(session.load(Model.class, 4));
            brand.addModel(session.load(Model.class, 5));

            session.save(brand);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
