package tests.web;

import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class KazanExpressTest extends TestBase {

    @Test
    void test() {
        step("Авторизация на сайте", () -> {open("https://kazanexpress.ru/");
            $("[data-test-id=button__auth]").click();
            $("[data-test-id=input__login]").setValue("Мария");
            $("[data-test-id=input__password]").setValue("cityslicka1A");
            $("[data-test-id=button__sign-in]").click();
        });
        step("Проверка авторизации", () -> {
            $("[data-test-id=text__name-modal-base]").shouldHave(text("Мария"));
        });
    }
}
