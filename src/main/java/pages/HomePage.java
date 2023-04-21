package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {
    private WebDriver driver;

    //кнопка "Заказать" в шапке
    private final By orderButtonUp = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[text() = 'Заказать']");
    // Кнопка "Заказать" в середине
    private final By orderButtonDown = By.xpath(".//div[@class = 'Home_RoadMap__2tal_']//button[text() = 'Заказать']");
    // куки
    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    //блок с вопросами
    private final By accordionBlock = By.xpath(".//*[@data-accordion-component = 'Accordion']");
    //раскрывающийся список
    private final By accordionPanel = By.xpath(".//*[@data-accordion-component = 'AccordionItemPanel']/p");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //нажать куки
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //нажать "Заказать" в шапке
    public void clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }

    //пролистать и нажать "Заказать" в середине
    public void clickOrderButtonDown() {
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }


    private WebElement getAccordionBlock() {
        return driver.findElement(accordionBlock);
    }

    private WebElement getAccordionItem(String questionHeader) {
        String accordionItemPath = ".//div[.//div[text() = '" + questionHeader + "'] and @data-accordion-component = 'AccordionItem']";
        return getAccordionBlock().findElement(By.xpath(accordionItemPath));
    }
    private WebElement getAccordionPanel(String questionHeader) {
        return getAccordionItem(questionHeader).findElement(accordionPanel);
    }

    public static void scrollToElement(WebDriver driver, WebElement scrollTo) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", scrollTo);
    }

    public static void waitVisibilityOfElement(WebDriver driver, WebElement waiting) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(waiting));
    }


    public String getAccordionText(String questionHeader) {
        WebElement accordionItem = getAccordionItem(questionHeader);

       scrollToElement(driver, accordionItem);
       waitVisibilityOfElement(driver, accordionItem);

        accordionItem.click();
        WebElement accordionPanel = getAccordionPanel(questionHeader);
        waitVisibilityOfElement(driver, accordionPanel);
        return accordionPanel.getText();
    }

}
