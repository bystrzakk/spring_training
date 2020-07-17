package test.demo.spring.core.dependency.injection.shop.components;

public class Roof {

    public Roof(int height) {
        this.height = height;
    }

    public Roof() {
    }

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
