package petukhova;
/*Дано: страница https://ru.wikipedia.org
Необходимо:
1) зайти на страницу
2) ввести произвольную строку в поиск
3) нажать на кнопку "Искать на страницах", после ввода
4) на полученной странице проверить, что результатов запроса больше, чем 1
Используйте selenium, паттерн PageObject и любой браузер на ваш вкус
*/

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

public class WikiPageTest {
    private final String url = "https://ru.wikipedia.org";
    private WebDriver driver;

    @BeforeClass
    public static void initProperties(){
        System.setProperty("webdriver.geko.driver", "gekodriver.exe");
    }

    @Before
    public void initWebDriver(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        driver = new FirefoxDriver(capabilities);
        //метод, возвращает опции(окно развернуть на максимум)
        driver.manage()
                .window()
                .maximize();

        driver.manage()
                .timeouts()
                .implicitlyWait(3, TimeUnit.SECONDS);

        //1) зайти на страницу
        driver.navigate().to(url);
    }

    @Test
    public void test(){
        try {
            //нажать на кнопку поиска
            WebElement searchButton1 = driver.findElement(By.name("go"));
            searchButton1.click();

            //найти окно поиска по css селектору
            WebElement searchInputElement1 = driver.findElement(By.cssSelector("#ooui-php-1"));

            //2) ввести произвольную строку в поиск
            searchInputElement1.sendKeys("Франция");

            //3) нажать на кнопку "Искать на страницах", после ввода
            WebElement searchButton2 = driver.findElement(By.cssSelector("button.oo-ui-inputWidget-input"));
            searchButton2.click();

            //4) на полученной странице проверить, что результатов запроса больше, чем 1
            //найти по css селектору место, где хранится значение количества найденных страниц
            WebElement searchInputElement2 = driver.findElement(By.cssSelector(".results-info"));
            //для переменной вызвать метод getAttribute(),
            //который возвращает значение свойства синхронизированного с атрибутом.
            //значение преобразовать в тип int
            int value = Integer.parseInt(searchInputElement2.getAttribute("data-mw-num-results-total"));
            //метод assertTrue сравнивает значения, если условие возращает true, то тест пройден
            assertTrue(value > 1);

        } catch (WebDriverException e){
            //если ошибка, то напечатать
            e.printStackTrace();
        }
    }

    @After
    public void closeDriver(){
        driver.quit();
    }
}
