package test.demo.spring.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
// Database Entity
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int horsePower;
    private int seats;
    private String color;
}