package data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HomePage {

    private final WebDriver driver;
    private final By orderButtonFromContent = By.xpath(" .//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");
    private final By orderButtonFromHeaderNav = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text()='Заказать']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void openAccordionQuestion(int question) {
        driver.findElement(By.xpath(String.format(".//div[@id='accordion__heading-%s']", question))).click();
    }

    public void assertAccordionAnswer(String accordionAnswer) {
        driver.findElement(By.xpath(String.format(".//p[text()='%s']", accordionAnswer))).isDisplayed();
    }

    public void clickOrderButtonFromContent() {
        driver.findElement(orderButtonFromContent).click();
    }

    public void clickOrderButtonFromHeaderNav() {
        driver.findElement(orderButtonFromHeaderNav).click();
    }

    public void acceptCookies() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
}
