package com.company.springmvcweb.data;


import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private static SessionFactory factory;

    public CourseRepository() {
        try {
            factory = new Configuration().
                    configure().
                    addAnnotatedClass(Participant.class).
                    addAnnotatedClass(Course.class).
                    buildSessionFactory();
//            factory = new Configuration().configure("com/company/hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //view all courses
    public List getAllCourses() {
        var session = factory.openSession();

        try {
            var courses = session.createQuery("FROM Course").list();
            return courses;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList();
    }

    //view all participants
    public List getAllParticipants() {
        var session = factory.openSession();

        try {
            var courses = session.createQuery("FROM Participant").list();
            return courses;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList();
    }


    //view all courses per industry
    public List getCoursesPerIndustry(Industry industry) {
        var session = factory.openSession();

        try {
            var courses = session.createQuery("FROM Course where industry='" + industry + "'").list();
            return courses;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return new ArrayList();
    }

    //view all courses per price range
    public Iterable<Course> getCoursesPerPrice(int priceFrom, int priceTo) {
        var session = factory.openSession();

        try {
            var courses = session.createQuery("FROM Course where price between " + priceFrom + "and " + priceTo).list();
            return courses;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    //view all courses participant has registered for
    public Iterable<Course> getCoursesPerParticipant(Integer participantId) {
        var session = factory.openSession();

        try {
            return session.createQuery("SELECT c FROM Course as c JOIN CourseParticipant as cp ON c.id=cp.courseId where cp.participantId=" + participantId).list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    //get participant's ID if e-mail address is provided
    public Integer getParticipantId(String eMail) {
        var session = factory.openSession();

        try {
            return (Integer) session.createQuery("SELECT id FROM Participant where e_mail='" + eMail + "'").uniqueResult();

        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }
        return null;
    }

    // deduct 1 slot from the available slots and save in DB
    public void decreaseFreeSlots(int courseId) {
        var session = factory.openSession();
        Transaction tx = null;
        try {
            var freeSlots = (Integer) session.createQuery("SELECT freeSlots FROM Course where id=" + courseId).uniqueResult();

            if (freeSlots > 0) {
                var freeSlotsNew = freeSlots - 1;

                Course c = session.get(Course.class, courseId);
                c.setFreeSlots(freeSlotsNew);
                tx = session.beginTransaction();
                session.update(c);
                tx.commit();
            } else {
                System.out.println("Sorry, no slots available");
                //varbūt jādara vēl kkas, piem., jābloķē pieteikšanās poga UI
            }

        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }

    }

    // increase 1 slot from the available slots and save in DB
    public void increaseFreeSlots(int courseId) {
        var session = factory.openSession();
        Transaction tx = null;
        try {
            var freeSlots = (Integer) session.createQuery("SELECT freeSlots FROM Course where id=" + courseId).uniqueResult();
            var freeSlotsNew = freeSlots + 1;

            Course c = session.get(Course.class, courseId);
            c.setFreeSlots(freeSlotsNew);
            tx = session.beginTransaction();
            session.update(c);
            tx.commit();

        } catch (HibernateException exception) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }

    }

}



