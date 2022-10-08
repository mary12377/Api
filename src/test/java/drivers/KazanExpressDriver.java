package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.KazanExpressConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class KazanExpressDriver implements WebDriverProvider {

    static KazanExpressConfig configLocal = ConfigFactory.create(KazanExpressConfig.class, System.getProperties());
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(configLocal.platformName());
        options.setDeviceName(configLocal.device());
        // options.setPlatformVersion("10.0");
        options.setApp(app.getAbsolutePath());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(configLocal.appURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    private File getApp() {

        String appUrl = "https://apkpure.com/ru/kazanexpress-%E2%80%94-" +
                "%D0%BC%D0%B0%D1%80%D0%BA%D0%B5%D1%82%D0%BF%D0%BB%D0%B5%D0%B9%D1%81/com.kazanexpress.ke_app/download";
        String appPath = "src/test/resources/apps/KazanExpress — Маркетплейс_v1.22.1_apkpure.com.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }

}