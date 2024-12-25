package data;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final WebDriver driver;
    private final By nameField = By.cssSelector("input[placeholder='* Имя']");
    private final By secondNameField = By.cssSelector("input[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private final By phoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    private final By commentField = By.cssSelector("input[placeholder='Комментарий для курьера']");
    private final By dateField = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-root");
    private final By metroSelectField = By.className("select-search__input");
    private final By nextButton = By.xpath(" .//div[@class='Order_NextButton__1_rCA']//button");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSecondName(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setMetro(String metro) {
        driver.findElement(metroSelectField).click();
        driver.findElement(By.xpath(String.format("//*[text()='%s']", metro))).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void chooseRentalPeriod(String dayCount) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", dayCount))).click();
    }

    public void chooseScooterColor(boolean black) {
        if (black) {
            driver.findElement(By.id("black")).click();
        } else {
            driver.findElement(By.id("grey")).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    public void checkOrderDone() {
        driver.findElement(By.xpath(".//div[text()='Заказ оформлен']")).isDisplayed();
    }
}
