package test.demo.spring.core.dependency.injection.shop;

import test.demo.spring.core.dependency.injection.shop.components.Floor;
import test.demo.spring.core.dependency.injection.shop.components.Roof;
import test.demo.spring.core.dependency.injection.shop.components.Wall;

public class Shop {

    private Floor floor;
    private Roof roof;
    private Wall wall;

    public Shop() {
        this.floor = new Floor();
        this.roof = new Roof();
        this.wall = new Wall();
    }

    private void buildRoof() {
        roof.setHeight(20);
    }

    private void buildWall() {
        wall.setColor("White");
        wall.setWindows(6);
    }

    private void buildFloor() {
        floor.setColor("Gray");
        floor.setHeight(10);
        floor.setWidth(20);
    }

    public void buildShop() {
        this.buildFloor();
        this.buildRoof();
        this.buildWall();
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
