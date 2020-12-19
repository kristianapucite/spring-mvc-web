//package com.company.springmvcweb.data;
//
//import com.company.springmvcweb.dto.CarSearchDto;
//import org.hibernate.HibernateException;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.util.ArrayList;
//
//public class CarsRepository {
//    private static SessionFactory factory;
//
//    public CarsRepository() {
//        try {
//            factory = new Configuration().
//                    configure().
//                            addAnnotatedClass(Owner.class).
//                            addAnnotatedClass(Car.class).
//                            buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public Iterable<Owner> getOwners() {
//        var session = factory.openSession();
//
//        try {
//            return session.createQuery("FROM Owner").list();
//        } catch (HibernateException exception) {
//            System.err.println(exception);
//        } finally {
//            session.close();
//        }
//
//        return new ArrayList<>();
//    }
//
//    public Iterable<Car> getCars() {
//        var session = factory.openSession();
//
//        try {
//            return session.createQuery("FROM Car").list();
//        } catch (HibernateException exception) {
//            System.err.println(exception);
//        } finally {
//            session.close();
//        }
//
//        return new ArrayList<>();
//    }
//
//    public Object getCar(int id) {
//        var session = factory.openSession();
//
//        try {
//            //var car = session.get(Car.class, id);
//
//            var sql = "FROM Car where id = :id";
//            var query = session.createQuery(sql);
//            query.setParameter("id", id);
//
//            var items = query.list();
//
//            //var item = items.get(0);
//
//            return items.size() > 0 ? items.get(0) : null;
//        } catch (HibernateException exception) {
//            System.err.println(exception);
//        } finally {
//            session.close();
//        }
//
//        return null;
//    }
//
//    public Iterable<Car> getCars(CarSearchDto searchDto) {
//        var session = factory.openSession();
//
//        try {
//            var sql = "FROM Car";
//
//            if(!searchDto.getNumber().isBlank() || !searchDto.getVinNumber().isBlank()) {
//                sql += " where ";
//            }
//
//            if(!searchDto.getNumber().isBlank()) {
//                sql += " number = :search_number";
//            }
//
//            if(!searchDto.getVinNumber().isBlank()) {
//
//                if(!searchDto.getNumber().isBlank()) {
//                    sql += " and ";
//                }
//
//                sql += " vin_number = :search_vin_number";
//            }
//
//            var query = session.createQuery(sql);
//
//            if(!searchDto.getNumber().isBlank()) {
//                query.setParameter("search_number", searchDto.getNumber());
//            }
//
//            if(!searchDto.getVinNumber().isBlank()) {
//                query.setParameter("search_vin_number", searchDto.getVinNumber());
//            }
//
//            return query.list();
//
//        } catch (HibernateException exception) {
//            System.err.println(exception);
//        } finally {
//            session.close();
//        }
//
//        return new ArrayList<>();
//    }
//}
