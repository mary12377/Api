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

        String appUrl = "https://trashbox.ru/files30/1708621/com.kazanexpress.ke_app_1.22.2_7800.apk/";
        String appPath = "src/test/resources/apps/com.kazanexpress.ke_app_1.22.2_7800.apk";

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