package pagesscooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//import static helper.Helper.waitTextToBePresentInElementLocated;
//import static helper.Helper.waitVisibilityOfElementLocated;

public class OrderPage {
    private WebDriver driver;

    // Поле "Заголовок страницы"
    private final By orderLabelPage = By.className("Order_Header__BZXOb");
    // Поле "Имя"
    private final By orderInputName = By.xpath(".//input[@placeholder = '* Имя']");
    // Поле "Фамилия"
    private final By orderInputSecondName = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Поле "Адрес"
    private final By orderInputAddress = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    private final By orderInputMetro = By.xpath(".//input[@placeholder = '* Станция метро']");
    // Пункт станции метро
    private final By orderInputItemMetro = By.className("select-search__row");
    // Поле "Телефон"
    private final By orderInputPhone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By orderButtonThen = By.xpath(".//button[text() = 'Далее']");

    // Поле "Когда привести"
    private final By orderInputWhen = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    // Поле "Срок аренды"
    private final By orderInputRentalPeriod = By.className("Dropdown-root");
    // Пункт "Срок аренды"
    public final By orderItemRentalPeriod = By.className("Dropdown-option");
    // Поле "Цвет самоката"
    private final By orderInputCheckboxColorBlack = By.id("black");
    private final By orderInputCheckboxColorGrey = By.id("grey");
    // Поле "Коммент"
    private final By orderInputComment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By orderButtonRequest = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    // Модальное окно
    private final By orderModal = By.className("Order_Modal__YZ-d3");
    // Кнопка "Да"
    private final By orderButtonYes = By.xpath(".//button[text() = 'Да']");
    // Текст в модальном окне
    private final By orderModalText = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setOrderInputName(String name) {
        driver.findElement(orderInputName).sendKeys(name);
    }

    public void setOrderInputSecondName(String secondName) {
        driver.findElement(orderInputSecondName).sendKeys(secondName);
    }

    public void setOrderInputAddress(String address) {
        driver.findElement(orderInputAddress).sendKeys(address);
    }

    public void setOrderInputItemMetro(String metro) {
        driver.findElement(orderInputMetro).sendKeys(metro);
        driver.findElement(orderInputItemMetro).click();
    }

    public void setOrderInputPhone(String phone) {
        driver.findElement(orderInputPhone).sendKeys(phone);
    }

    public void clickOrderButtonThen() {
        driver.findElement(orderButtonThen).click();
    }

    public void setOrderInputWhen(String when) {
        driver.findElement(orderInputWhen).sendKeys(when);
        driver.findElement(orderLabelPage).click();
    }

    public void setOrderItemRentalPeriod(int number) {
        driver.findElement(orderInputRentalPeriod).click();
        List<WebElement> dropdownOptions = driver.findElements(orderItemRentalPeriod);
        dropdownOptions.get(number).click();
    }

    public void setOrderInputCheckboxColor(String color) {
        if (color.equals("Черный")) {
            driver.findElement(orderInputCheckboxColorBlack).click();
        } else {
            driver.findElement(orderInputCheckboxColorGrey).click();
        }
    }

    public void setOrderInputComment(String comment) {
        driver.findElement(orderInputComment).sendKeys(comment);
    }

    public void clickOrderButtonRequest() {
        driver.findElement(orderButtonRequest).click();
    }

    public void clickOrderButtonYes() {
        driver.findElement(orderButtonYes).click();
    }

    public String getOrderLabelSuccess() {
        return driver.findElement(orderModalText).getText();
    }

    public static void waitVisibilityOfElementLocated(WebDriver driver, By waiting) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(waiting));
    }

    public static void waitTextToBePresentInElementLocated(WebDriver driver, By waiting, String changed) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.textToBePresentInElementLocated(waiting, changed));
    }



    public void editOrderPageOne(String name, String secondName, String address,String metro , String phone) {
         waitVisibilityOfElementLocated(driver, orderLabelPage);
        setOrderInputName(name);
        setOrderInputSecondName(secondName);
        setOrderInputAddress(address);
        setOrderInputItemMetro(metro);
        setOrderInputPhone(phone);
    }

    public void editOrderPageTwo(String when, int itemRentalPeriod, String color, String comment) {
       waitTextToBePresentInElementLocated(driver, orderLabelPage, "Про аренду");
        setOrderInputWhen(when);
        setOrderItemRentalPeriod(itemRentalPeriod);
        setOrderInputCheckboxColor(color);
        setOrderInputComment(comment);
        clickOrderButtonRequest();
    }

    public void checkModal() {
       waitVisibilityOfElementLocated(driver, orderModal);
        clickOrderButtonYes();
       waitTextToBePresentInElementLocated(driver, orderModalText, "Заказ оформлен");
    }

}
