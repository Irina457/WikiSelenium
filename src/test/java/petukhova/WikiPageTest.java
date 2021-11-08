package petukhova;
/*Дано: страница https://ru.wikipedia.org
Необходимо:
1) зайти на страницу
2) ввести произвольную строку в поиск
3) нажать на кнопку "Искать на страницах", после ввода
4) на полученной странице проверить, что результатов запроса больше, чем 1
Используйте selenium, паттерн PageObject и любой браузер на ваш вкус
*/

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriverException;

import static com.codeborne.selenide.Condition.*;
import static org.junit.Assert.assertTrue;

public class WikiPageTest {

    @Before
    public void openStartPage(){
        //зайдет в интернет, скачает образец драйвера
        Configuration.browser = "firefox";  //объявление браузера
        Configuration.startMaximized = true;    //максимального размера для окна

        Selenide.open(WikiSearchPage.getBASE_URL());  //открыть теущую страницу

    }

    @Test
    public void test(){
        try{
            String searchText = "Франция";  //сделать поиск

            WikiSearchPage searchPage = new WikiSearchPage();   //создание экземпляра новой страницы
            searchPage.getSearchButton()    //поиск кнопки
                    .click();   //нажатие на кнопку

            searchPage.getSearchField()     //взять поля
                    .shouldBe(enabled, visible, empty)
                    .setValue(searchText);  //ввести нужный текст

            searchPage.getSearchButton2()    //поиск кнопки
                    .click();   //нажатие на кнопку

            WikiResultPage resultPage = new WikiResultPage();
            resultPage.getSearchInput()
                    .shouldBe(visible, enabled);
            int size = resultPage.getResults()
                    .size();

            assertTrue(size > 1);

        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }

    @After
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
}
