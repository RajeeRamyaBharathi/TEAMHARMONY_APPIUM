package Utilities;

import io.appium.java_client.android.AndroidDriver;

public class drivermanager {
	
	public static AndroidDriver driver;
	
	public static void setdriver(AndroidDriver driverinstance) {
		driver = driverinstance;
	}
	
	public static AndroidDriver getdriver() {
		return driver;
	};


}
