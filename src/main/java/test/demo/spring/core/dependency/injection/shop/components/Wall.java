package test.demo.spring.core.dependency.injection.shop.components;

public class Wall {
    private int windows;
    private String color;

    public Wall() {
    }

    public Wall(int windows, String color) {
        this.windows = windows;
        this.color = color;
    }

    public int getWindows() {
        return windows;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
