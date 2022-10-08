package config;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config/local.properties")
public interface KazanExpressConfig extends Config {

    @DefaultValue("android")
    String platformName();

    @DefaultValue("Pixel 3 API 33")
    String device();


    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    String appURL();

}
