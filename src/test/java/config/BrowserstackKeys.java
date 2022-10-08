package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/browserstack.properties"
})
public interface BrowserstackKeys extends Config {

    String login();

    String password();

    @DefaultValue("QA.GURU lesson 12/21")
    String project();

    String build();

    @DefaultValue("selenide android test")
    String name();

    @DefaultValue("bs://be7df9b0372faab5e8aa57e9db4c88850545a65f")
    String app();

    @DefaultValue("Google Pixel 3a xl")
    String device();

    String osVersion();

    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String baseUrl();

}
