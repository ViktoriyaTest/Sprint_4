package chromedriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import  org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriver {

    protected ChromeDriver driver;


    // создать драйвер для браузера Chrome (метод родитель)
    @Before
    public void chromeSetUp (){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {driver.quit();}

}
