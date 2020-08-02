package test.demo.spring.core.converter;

import test.demo.spring.core.dto.CarDto;
import test.demo.spring.core.model.Car;

public class CarConverter {

    public static CarDto mapToDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .color(car.getColor())
                .horsePower(car.getHorsePower())
                .seats(car.getSeats())
                .build();
    }

    public static Car mapFromDto(CarDto car) {
        return Car.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .color(car.getColor())
                .horsePower(car.getHorsePower())
                .seats(car.getSeats())
                .build();
    }
}
