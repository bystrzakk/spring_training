package test.demo.spring.core.dependency.injection.shop;

import test.demo.spring.core.dependency.injection.shop.components.Floor;
import test.demo.spring.core.dependency.injection.shop.components.Roof;
import test.demo.spring.core.dependency.injection.shop.components.Wall;

public class ShopDi {

    private Floor floor;
    private Roof roof;
    private Wall wall;

    public ShopDi(Floor floor, Wall wall, Roof roof) {
        this.floor = floor;
        this.roof = roof;
        this.wall = wall;
    }

    public Floor getFloor() {
        return floor;
    }

    public Roof getRoof() {
        return roof;
    }

    public Wall getWall() {
        return wall;
    }
}
