package com.company.springmvcweb.data;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class CarsRepository {
    private static SessionFactory factory;

    public CarsRepository() {
        try {
            factory = new Configuration().
                    configure().
                            addAnnotatedClass(Owner.class).
                            addAnnotatedClass(Car.class).
                            buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Owner> getOwners() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Owner").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public Iterable<Car> getCars() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Car").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }
}
