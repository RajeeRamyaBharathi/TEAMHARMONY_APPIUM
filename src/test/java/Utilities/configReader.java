package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configReader {

    private static Properties prop = new Properties();


    static {
        try (InputStream input = configReader.class.getClassLoader().getResourceAsStream("Config.properties")) {
            if (input == null) {
                throw new IOException("Config.properties file not found in classpath!");
            }
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String device() {
        return prop.getProperty("device");
    }

    public String applocation() {
        return prop.getProperty("applocation");
    }

    public String appiumserver() {
        return prop.getProperty("appiumserver");
    }

    public String emulatorName() {
        return prop.getProperty("emulatorName");
    }
    public String appPackage() {
        return prop.getProperty("appPackage");
    }

    public String appActivity() {
        return prop.getProperty("appActivity");
    }

}
