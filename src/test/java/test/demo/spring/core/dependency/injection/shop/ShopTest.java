package test.demo.spring.core.dependency.injection.shop;


import org.junit.Assert;
import org.junit.Test;
import test.demo.spring.core.dependency.injection.shop.components.Floor;
import test.demo.spring.core.dependency.injection.shop.components.Roof;
import test.demo.spring.core.dependency.injection.shop.components.Wall;

public class ShopTest {

    @Test
    public void testWithoutDI(){
        Shop shop = new Shop();
        shop.buildShop();
        Assert.assertEquals("Gray", shop.getFloor().getColor());
    }

    @Test
    public void testWithDI(){
        Wall wall = new Wall(6, "Red");
        Floor floor = new Floor("Green", 12, 12);
        Roof roof = new Roof(10);

        ShopDi shopDi = new ShopDi(floor, wall, roof);

        Assert.assertEquals("Green", shopDi.getFloor().getColor());
    }


}