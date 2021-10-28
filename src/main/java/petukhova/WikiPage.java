package petukhova;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WikiPage {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty("webdriver.geko.driver", "gekodriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        WebDriver driver = new FirefoxDriver(capabilities);
        //метод, возвращает опции(окно развернуть на максимум)
        driver.manage()
                .window()
                .maximize();

        try {
            //1) зайти на страницу
            driver.navigate().to("https://ru.wikipedia.org");

            //нажать на кнопку поиска
            WebElement searchButton1 = driver.findElement(By.name("go"));
            searchButton1.click();

            //найти элемент по css селектору
            WebElement searchInputElement = driver.findElement(By.cssSelector("#ooui-php-1"));

            //в элементе ввести следующую строку
            //2) ввести произвольную строку в поиск
            searchInputElement.sendKeys("Франция");

            //3) нажать на кнопку "Искать на страницах", после ввода
            //найти элемент
            WebElement searchButton2 = driver.findElement(By.cssSelector("button.oo-ui-inputWidget-input"));
            searchButton2.click();

        } catch (WebDriverException e){
            //если ошибка, то напечатать
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
