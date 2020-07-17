package test.demo.spring.core.dependency.injection.shop.components;

public class Floor {
    private String color;
    private int width;
    private int height;

    public Floor() {
    }

    public Floor(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
