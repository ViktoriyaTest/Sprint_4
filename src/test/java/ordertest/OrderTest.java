package ordertest;
import chromedriver.WebDriver;
//import driver.UseWebDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
//import pages.HomePage;
//import pages.OrderPage;
import pagesscooter.HomePage;
import pagesscooter.OrderPage;

import java.util.Objects;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class OrderTest extends WebDriver {
    private final String button;
    private final String name;
    private final String secondName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String deliveryDate;
    private final int rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String button, String name, String secondName, String address, String metro, String phone, String deliveryDate, int rentalPeriod, String color, String comment) {
        this.button = button;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"ButtonUp", "Виктория", "Учкина", "ул.Программистов, 4", "Войковская", "89991112332", "25.04.2023", 1, "Черный", "Коммент"},
                {"ButtonDown", "Сергей", "Грачин", "Дмитровское шоссе, 5", "Дмитровская", "89150003434", "01.05.2023", 2, "Серый", "Коммент"},
        };
    }

    @Test
    public void checkOrder() {
        HomePage objHomePage = new HomePage(driver);
        objHomePage.clickCookieButton();

        if (Objects.equals(button, "ButtonUp")){
            objHomePage.clickOrderButtonUp();
        }
        if (Objects.equals(button, "ButtonDown")) {
            objHomePage.clickOrderButtonDown();
        }
        OrderPage objOrderPageScooter = new OrderPage(driver);
        objOrderPageScooter.editOrderPageOne(
                name,
                secondName,
                address,
                metro,
                phone);
        objOrderPageScooter.clickOrderButtonThen();
        objOrderPageScooter.editOrderPageTwo(
                deliveryDate,
                rentalPeriod,
                color,
                comment);
        objOrderPageScooter.checkModal();
        assertTrue(objOrderPageScooter.getOrderLabelSuccess().contains("Заказ оформлен"));
    }

}
