package tests.mobile;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class KazanExpressTest extends TestBase {


    @Test
    @DisplayName("Запускаем приложение")
    void loadingTheGameTest() {

        step("Запускаем игру и нажимаем на всех всплывающих окнах ОК ", () ->
                $(AppiumBy.id("com.kazanexpress.ke_app:id/pageIndicatorView")).click());
                $(AppiumBy.xpath("//android.widget.Button[@text='OK' and @index='2']")).click();
        step("Проверяем что игра загрузилась  ", () ->
                $(AppiumBy.xpath("//android.widget.FrameLayout[@index='0']")).shouldBe(Condition.visible));
    }

    @Test
    @DisplayName("Проверяем кликабельность кнопки Terms of Use")
    void clickTermsOfUseTest() {
        // switchTo().alert().accept();
        step("Запускаем игру и нажимаем на кнопку Terms of Use ", () ->
                $(AppiumBy.xpath("//android.widget.Button[@text='OK' and @index='0']")).click());
                $(AppiumBy.xpath("//android.widget.Button[@text='TERMS OF USE' and @index='0']")).click();
        step("Проверяем что появился текст Terms of Use  ", () ->
                $(AppiumBy.xpath("//android.widget.Button[@text='Close' and @index='0']")).click());
                $(AppiumBy.xpath("//android.widget.TextView[@text='Terms of Use' and @index='0']")).shouldHave(text("Terms of Use"));
    }

    @Test
    @DisplayName("Проверяем кликабельность кнопки Privacy Policy")
    void clickPrivacyPolicyTest() {
        // switchTo().alert().accept();
        step("Запускаем игру и нажимаем на кнопку Privacy Policy", () ->
                $(AppiumBy.xpath("//android.widget.Button[@text='OK' and @index='0']")).click());
                $(AppiumBy.xpath("//android.widget.Button[@text='PRIVACY POLICY' and @index='1']")).click();
        step("Проверяем что появился текст Privacy Policy  ", () ->
                $(AppiumBy.xpath("//android.widget.TextView[@text='Privacy Policy' and @index='0']")).shouldHave(text("Privacy Policy")));
    }

}
