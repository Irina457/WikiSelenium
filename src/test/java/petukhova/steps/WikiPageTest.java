package petukhova.steps;
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
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import petukhova.WikiResultPage;
import petukhova.WikiSearchPage;

import static com.codeborne.selenide.Condition.*;
import static org.junit.Assert.assertTrue;

public class WikiPageTest {

    @BeforeAll
    public static void setBrowserConfig(){
        //зайдет в интернет, скачает образец драйвера
        Configuration.browser = "firefox";  //объявление браузера
        Configuration.startMaximized = true;    //максимального размера для окна
    }

    @Given("^I open wiki search page$")
    public void openStartPage(){
        Selenide.open(WikiSearchPage.getBASE_URL());  //открыть теущую страницу
    }

    @Given("^I click the button")
    public void clickButton(){
        WikiSearchPage searchPage = new WikiSearchPage(); //создание экземпляра новой страницы
        searchPage.getSearchButton()    //поиск кнопки
                .click();   //нажатие на кнопку
    }

    @Given("^I write text \"(.+)\" to search input$")
    public void setTextToInput(String searchText){
        WikiSearchPage searchPage = new WikiSearchPage();
        searchPage.getSearchField()     //взять поля
                .shouldBe(enabled, visible, empty)
                .setValue(searchText);  //ввести нужный текст
    }

    @And("^I click the button to search it")
    public void clickToSearch(){
        WikiSearchPage searchPage = new WikiSearchPage();
        searchPage.getSearchButton2()    //поиск кнопки
                .click();   //нажатие на кнопку
    }

    @Then("Result page has count more than 1")
    public void checkResult(){
        WikiResultPage resultPage = new WikiResultPage();
        resultPage.getSearchInput()
                .shouldBe(visible, enabled);
        int size = resultPage.getResults()
                .size();

        assertTrue(size > 1);

    }

    @AfterAll
    public static void closeAll(){
        Selenide.closeWebDriver();
    }

}
