package test.demo.spring.core.service;

import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.CarConverter;
import test.demo.spring.core.dto.CarDto;
import test.demo.spring.core.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final Car car1 = new Car(1L, "BMW", "e30", 250, 5, "Red");
    private final Car car2 = new Car(2L, "Renault", "Megane", 280, 4, "Orange");
    private final Car car3 = new Car(3L, "Mercedes", "C", 190, 5, "Red");
    private final Car car4 = new Car(4L, "Honda", "Civic", 310, 5, "Champion White");
    private final Car car5 = new Car(5L, "Cupra", "Leon", 265, 5, "Silver");
    private final List<Car> carList = new ArrayList<>(Arrays.asList(car1, car2, car3, car4, car5));

    public List<CarDto> getAllCars(String color) {
        Predicate<Car> filter = car -> {
            if (color != null) {
                return car.getColor().equalsIgnoreCase(color);
            }
            return true;
        };

        return carList
                .stream()
                .filter(filter)
                .map(CarConverter::mapToDto)
                .collect(Collectors.toList());
    }

    public void addNewCar(CarDto newCar) {
        final Car car = CarConverter.mapFromDto(newCar);
        carList.add(car);
    }

    public CarDto getSingleCar(Long id) {
        final Car carById = getCarById(id);
        return CarConverter.mapToDto(carById);
    }

    public void updateCar(CarDto carDto) {
        final Car carById = getCarById(carDto.getId());

        if (carById != null) {
            carById.setBrand(carDto.getBrand());
            carById.setColor(carDto.getColor());
            carById.setModel(carDto.getModel());
            carById.setSeats(carDto.getSeats());
            carById.setHorsePower(carDto.getHorsePower());
        }
    }

    private Car getCarById(Long id) {
        for (Car car : carList) {
            if (id.equals(car.getId())) {
                return car;
            }
        }
        System.out.println("No car with given id: " + id);

        return null;
    }


}
