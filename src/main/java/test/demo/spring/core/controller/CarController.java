package test.demo.spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.dto.CarDto;
import test.demo.spring.core.service.CarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars(@RequestParam(value = "color", required = false) String color) {
        final List<CarDto> allCars = carService.getAllCars(color);
        return ResponseEntity.ok().body(allCars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id") Long id) {
        final CarDto singleCar = carService.getSingleCar(id);

        return ResponseEntity.ok().body(singleCar);
    }

    @PostMapping
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDto car) {
        carService.addNewCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateCar(@Valid @RequestBody CarDto car) {
        carService.updateCar(car);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id) {
        carService.removeCar(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testRest() {
        carService.testExternalService();

        return ResponseEntity.ok().build();
    }
}


