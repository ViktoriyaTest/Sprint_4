package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderPage {
    private WebDriver driver;

    private final By orderLabelPage = By.className("Order_Header__BZXOb"); //"Заголовок страницы"
    private final By orderInputName = By.xpath(".//input[@placeholder = '* Имя']");  //"Имя"
    private final By orderInputSecondName = By.xpath(".//input[@placeholder = '* Фамилия']"); // "Фамилия"
    private final By orderInputAddress = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");// "Адрес"
    private final By orderInputMetro = By.xpath(".//input[@placeholder = '* Станция метро']");//"Метро"
    private final By orderInputItemMetro = By.className("select-search__row"); //"выбор метро"
    private final By orderInputPhone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");// "Телефон"
    private final By orderButtonThen = By.xpath(".//button[text() = 'Далее']");// "Далее"

    private final By orderInputWhen = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");// Когда привезти
    private final By orderInputRentalPeriod = By.className("Dropdown-root");// "Срок аренды"
    public final By orderItemRentalPeriod = By.className("Dropdown-option");  // "Срок аренды"
    private final By orderInputCheckboxColorBlack = By.id("black");// Цвет черный
    private final By orderInputCheckboxColorGrey = By.id("grey"); //цвет серый
    private final By orderInputComment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");//коммент
    private final By orderButtonRequest = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");//заказать
    private final By orderModal = By.className("Order_Modal__YZ-d3");//окно подтвр
    private final By orderButtonYes = By.xpath(".//button[text() = 'Да']");
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
