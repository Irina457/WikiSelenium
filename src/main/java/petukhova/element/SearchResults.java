package petukhova.element;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;

@Data
public class SearchResults {
    private final String title;    //заголовок
    private final String partialText;   //текст результата
    private final String date;      //дата

    public SearchResults(SelenideElement container) {
        this.title = container.$(By.cssSelector("div.mw-search-result-heading"))
                .text();
        this.partialText = container.$("div.searchresult").text();
        this.date = container.$(By.cssSelector("div.mw-search-result-data")).text();
    }
}
