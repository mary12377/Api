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

        step(" ", () ->
                $(AppiumBy.id("com.kazanexpress.ke_app:id/pageIndicatorView")).click());
                $(AppiumBy.xpath("//android.view.View[@index='1']")).click();
        step("Проверяем что игра загрузилась  ", () ->
                $(AppiumBy.xpath("//android.widget.FrameLayout[@index='0']")).shouldBe(Condition.visible));
    }

}
