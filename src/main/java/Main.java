import Dao.CarDao;
import Entities.Car;
import Entities.GearTypes;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarDao carDao = new CarDao();
        Car car = new Car("Lambo", "10000", "01/02/2019",
                "20/12/2018", GearTypes.AUTOMATIC, 60);
        Car car2 = new Car("suzuli", "3000", "12/02/2009",
                "10/06/2005", GearTypes.SEMIAUTOMATIC, 70);
        carDao.saveCar(car);
        carDao.saveCar(car2);
        carDao.insertCar();

        Car car1 = new Car("KIA", "22000", "05/05/2015",
                "18/08/2020", GearTypes.MANUAL, 50);
        carDao.updateCarById(car1, 1);

        System.out.println(carDao.getCarById(2));

        List<Car> carsByTitle = carDao.getCarsByTitle("Mercedes");
        System.out.println(carsByTitle);

        System.out.println(carDao.getCarByPriceRange("20000", "31000"));

        List<Car> cars = carDao.getCars();
        cars.forEach(s -> System.out.println(s.getTitle()));

        carDao.deleteCarById(2);

        carDao.deleteCars();
    }
}