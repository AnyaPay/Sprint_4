import data.HomePage;
import data.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderScooter {
    private final String name;
    private final String secondName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final Boolean colorScooter;
    private final String comment;
    private final Boolean orderScooterFromHeader;

    public OrderScooter(Boolean orderScooterFromHeader, String name, String secondName, String address, String metro, String phone, String date, String rentalPeriod, Boolean colorScooter, String comment) {
        this.orderScooterFromHeader = orderScooterFromHeader;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {true, "Анна", "Иванова", "Тобольск", "Бульвар Рокоссовского", "89999999999", "31.11.2025", "двое суток", true, "Здесь комментарий"},
                {false, "Дмитрий", "Иванов", "Тверь", "Черкизовская", "89999999999", "01.01.2025", "сутки", false, "Здесь комментарий"},
        };
    }

    private WebDriver driver;

    @Test
    public void orderScooter() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        if (orderScooterFromHeader) {
            homePage.clickOrderButtonFromHeaderNav();
        }
        else {
            homePage.clickOrderButtonFromContent();
        }
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setName(name);
        orderPage.setSecondName(secondName);
        orderPage.setAddress(address);
        orderPage.setMetro(metro);
        orderPage.setPhone(phone);
        orderPage.clickNextButton();
        orderPage.chooseRentalPeriod(rentalPeriod);
        orderPage.chooseScooterColor(colorScooter);
        orderPage.setComment(comment);
        orderPage.setDate(date);
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();
        orderPage.checkOrderDone();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
