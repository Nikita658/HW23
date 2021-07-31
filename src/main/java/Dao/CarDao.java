package Dao;

import Entities.Car;
import Entities.GearTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernateUtils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class CarDao {
    public void saveCar(Car car) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Object object = session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void insertCar() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "INSERT INTO Car (title, price, manufactureDate, sellDate, gearType, fuelVolume)" +
                    "SELECT title, price, manufactureDate, sellDate, gearType, fuelVolume FROM Car";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCarById(Car car, int carId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE Car set title = :title, price = :price, manufactureDate = :manufactureDate, " +
                    "sellDate = :sellDate, gearType = :gearType, fuelVolume = :fuelVolume " + "WHERE id = :carId";
            Query query = session.createQuery(hql);
            query.setParameter("title", car.getTitle());
            query.setParameter("price", car.getPrice());
            query.setParameter("manufactureDate", car.getManufactureDate());
            query.setParameter("sellDate", car.getSellDate());
            query.setParameter("gearType", GearTypes.AUTOMATIC);
            query.setParameter("fuelVolume", car.getFuelVolume());
            query.setParameter("carId", carId);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Car getCarById(int id) {
        Transaction transaction = null;
        Car car = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Car C WHERE C.id = :carId";
            Query query = session.createQuery(hql);
            query.setParameter("carId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                car = (Car) results.get(0);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> getCarsByTitle(String title) {
        List<Car> cars = null;
        Transaction transaction = null;
        Car car = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Car C WHERE C.title LIKE :carTitle";
            Query query = session.createQuery(hql);
            query.setParameter("carTitle", title);
            cars = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarByPriceRange(String min, String max) {
        Transaction transaction = null;
        List<Car> cars = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Car C WHERE C.price BETWEEN :min AND :max";
            Query query = session.createQuery(hql);
            query.setParameter("min", min);
            query.setParameter("max", max);
            cars = query.getResultList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCars() {
        List<Car> cars = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            cars = session.createQuery("from Car", Car.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void deleteCarById(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Car car = session.get(Car.class, id);
            if (car != null) {
                String hql = "DELETE FROM Car " + "WHERE id = :carId";
                Query query = session.createQuery(hql);
                query.setParameter("carId", id);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCars() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Car";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}