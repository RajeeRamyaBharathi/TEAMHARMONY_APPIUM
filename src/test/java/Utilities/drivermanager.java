package Utilities;

import io.appium.java_client.android.AndroidDriver;

public class drivermanager {
	
	private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();
	public static void setdriver(AndroidDriver driverInstance) {
		driverThreadLocal.set(driverInstance);
	}
	
	public static AndroidDriver getdriver() {
		 return driverThreadLocal.get();
	}
	
	public static void unload() {
	        driverThreadLocal.remove();
	}
	
}
