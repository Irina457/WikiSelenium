package petukhova;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.By;
import petukhova.element.SearchResults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data

public class WikiResultPage {
    private final SelenideElement searchInput = $(By.cssSelector("input[id=\"ooui-php-1\"]"));

    //выдать результаты
    public Collection<SearchResults> getResults(){
        List<SearchResults> resultList = new ArrayList<>();
        //пройтись по всем элементам
        for(SelenideElement result : $$("ul.mw-search-results li.mw-search-result")){
            //каждый из результатов поместить в конструктор
            resultList.add(new SearchResults(result));
        }
        return resultList;
    }
}
