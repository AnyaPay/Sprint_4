import data.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class HomeFAQ {
    private final String question;
    private final String answer;

    public HomeFAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters
    public static Object[][] getAccordion() {
        return new Object[][] {
                {".//div[@id='accordion__heading-0']", ".//p[text()='Сутки — 400 рублей. Оплата курьеру — наличными или картой.']"},
                {".//div[@id='accordion__heading-1']", ".//p[text()='Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.']"},
                {".//div[@id='accordion__heading-2']", ".//p[text()='Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.']"},
                {".//div[@id='accordion__heading-3']", ".//p[text()='Только начиная с завтрашнего дня. Но скоро станем расторопнее.']"},
                {".//div[@id='accordion__heading-4']", ".//p[text()='Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.']"},
                {".//div[@id='accordion__heading-5']", ".//p[text()='Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.']"},
                {".//div[@id='accordion__heading-6']", ".//p[text()='Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.']"},
                {".//div[@id='accordion__heading-7']", ".//p[text()='Да, обязательно. Всем самокатов! И Москве, и Московской области.']"},
        };
    }

    private WebDriver driver;

    @Test
    public void homeFAQTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("accordion")));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        HomePage homePage = new HomePage(driver);
        homePage.openAccordionQuestion(question);
        homePage.assertAccordionAnswer(answer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
