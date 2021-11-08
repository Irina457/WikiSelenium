package petukhova;

//найти окно поиска
//3) нажать на кнопку "Искать на страницах", после ввода

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Data
public class WikiSearchPage {
    @Getter
    private static final String BASE_URL = "https://ru.wikipedia.org";  //url всегда один

    private final SelenideElement searchField = $(By.name("search"));   //найти окно поиска
    private final SelenideElement searchButton = $(By.name("go"));      //кнопка поиска
    private final SelenideElement searchButton2 = $(By.cssSelector("button.oo-ui-inputWidget-input"));  //кнопка Найти

}

