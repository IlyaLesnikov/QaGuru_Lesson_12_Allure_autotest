package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GitHubTest {
    private final String REPOSITORY_NAME = "/eroshenkoam/allure-example";
    private final String ISSUE_NAME = "С Новым Годом (2022)";
    @Test
    @Description("Проверка наименования Issue в репозитории через лямба степы")
    protected void checkingTheIssueNameInTheRepositoryLambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> open("https://github.com/"));
        step("Открываем строку поиска и вводим название репозитория", () -> {
            $("[data-target='qbsearch-input.inputButtonText']").click();
            $("[data-action*='input:query-builder']").setValue(REPOSITORY_NAME).pressEnter();
        });
        step("Нажатие на первый репозиторий страницы", () -> $("[class*='search-match']").click());
        step("Нажатие на таб Issue", () -> $("#issues-tab").click());
        step("Проверка наименования Issue", () -> $$("[data-hovercard-type='issue']")
                .findBy(text(ISSUE_NAME))
                .shouldHave(text(ISSUE_NAME)));
    }
    @Test
    @Description("Проверка наименования Issue в репозитории через аллюр степы")
    protected void checkingTheIssueNameInTheRepositoryAllureStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();
        steps.openWebSite();
        steps.openTheFirstRepository();
        steps.openTabIssue();
        steps.checkingTheIssueName(REPOSITORY_NAME);
    }
    @Test
    @Description("Проверка наименования Issue в репозитории через селенид степы")
    protected void checkingTheIssueNameInTheRepositorySelenideStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[data-action*='input:query-builder']").setValue(REPOSITORY_NAME).pressEnter();
        $("[class*='search-match']").click();
        $("#issues-tab").click();
        $$("[data-hovercard-type='issue']")
                .findBy(text(ISSUE_NAME))
                .shouldHave(text(ISSUE_NAME));
    }
}
