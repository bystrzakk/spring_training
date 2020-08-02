package test.demo.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.demo.spring.core.converter.CarConverter;
import test.demo.spring.core.dto.CarDto;
import test.demo.spring.core.model.Car;
import test.demo.spring.core.repository.CarRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CarService {

    private RestTemplate restTemplate;
    private CarRepository carRepository;

    @Autowired
    public CarService(RestTemplate restTemplate, CarRepository carRepository) {
        this.restTemplate = restTemplate;
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars(String color) {
        Predicate<Car> filter = car -> {
            if (color != null) {
                return car.getColor().equalsIgnoreCase(color);
            }
            return true;
        };

        final List<Car> carList = carRepository.findAll();

        return carList
                .stream()
                .filter(filter)
                .map(CarConverter::mapToDto)
                .collect(Collectors.toList());
    }

    public void addNewCar(CarDto newCar) {
        final Car car = CarConverter.mapFromDto(newCar);
        carRepository.save(car);
    }

    public void removeCar(Long id) {
        carRepository.delete(getCarById(id));
        System.out.println("Car removed with success!");
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
        carRepository.save(carById);
    }

    private Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    public void testExternalService() {
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9090/api/test", String.class);
        System.out.println("External service returned: " + forEntity.getBody());
    }


}
