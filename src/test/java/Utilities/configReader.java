package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configReader {

    private static Properties prop = new Properties();

    // Load once in a static block
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
}
