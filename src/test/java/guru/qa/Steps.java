package guru.qa;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Steps {
    @Step("Открываем главную страницу")
    public void openWebSite() {
        open("https://github.com/");
    }
    @Step("Открываем строку поиска и вводим название репозитория")
    public void openTheSearchBarAndEnterTheNameOfTheRepository(String repositoryName) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[data-action*='input:query-builder']").setValue(repositoryName).pressEnter();
    }
    @Step("Нажатие на первый репозиторий страницы")
    public void openTheFirstRepository() {
        $("[class*='search-match']").click();
    }
    @Step("Нажатие на таб Issue")
    public void openTabIssue() {
        $("#issues-tab").click();
    }
    @Step("Проверка наименования Issue")
    public void checkingTheIssueName(String issueName) {
        $$("[data-hovercard-type='issue']")
                .findBy(text(issueName))
                .shouldHave(text(issueName));
    }

}
