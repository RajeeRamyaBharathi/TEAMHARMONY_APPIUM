package Hooks;

import java.io.IOException;
import java.net.URI;
import Utilities.configReader;
import Utilities.drivermanager;
import Utilities.loggerLoad;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {
	
	String path;
	UiAutomator2Options options;
	AndroidDriver driver;
	AppiumDriverLocalService service;
	Process emulatorProcess;
	
	@Before
	public void connectdevice() throws Throwable {
		
		configReader config = new configReader();
		String deviceId = config.device();

        // Check if emulator is already running
        if (!isEmulatorRunning(deviceId)) {
            startEmulator(config.emulatorName());
            waitForDeviceToBoot();
        } else {
            loggerLoad.info("Emulator already running: " + deviceId);
        }

        // Start Appium server
        startAppiumServer();

		
		String path = System.getProperty("user.dir")+config.applocation();
		UiAutomator2Options options = new UiAutomator2Options()
				.setAppWaitActivity("*")
			    .setUdid(config.device())
		    .setApp(path);
	driver= new AndroidDriver(new URI(config.appiumserver()).toURL(), options);
		
   drivermanager drivermanage = new drivermanager();
    drivermanage.setdriver(driver);
	loggerLoad.info("Successfully connected to device");
	}
	
	@After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
        if (emulatorProcess != null) {
            emulatorProcess.destroy();
        }
    }

	private boolean isEmulatorRunning(String deviceId) throws IOException {
        Process process = new ProcessBuilder("adb", "devices").start();
        byte[] output = process.getInputStream().readAllBytes();
        String devicesList = new String(output);
        return devicesList.contains(deviceId);
    }

    private void startEmulator(String emulatorName) throws IOException {
        String androidHome = System.getenv("ANDROID_HOME");
        if (androidHome == null || androidHome.isEmpty()) {
            throw new RuntimeException("ANDROID_HOME environment variable is not set.");
        }
        String emulatorPath = androidHome + "/emulator/emulator";

        emulatorProcess = new ProcessBuilder(emulatorPath, "-avd", emulatorName)
                .redirectErrorStream(true)
                .start();

        logProcessOutput(emulatorProcess);

        loggerLoad.info("Emulator " + emulatorName + " started.");
    }

    private void logProcessOutput(Process process) {
        new Thread(() -> {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    loggerLoad.info("[Emulator] " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
 
    private void waitForDeviceToBoot() throws IOException, InterruptedException {
        String[] command = { "adb", "shell", "getprop", "sys.boot_completed" };
        boolean booted = false;
        while (!booted) {
            Process process = new ProcessBuilder(command).start();
            byte[] output = process.getInputStream().readAllBytes();
            String result = new String(output).trim();
            if ("1".equals(result)) {
                booted = true;
                loggerLoad.info("Emulator boot completed.");
            } else {
                Thread.sleep(2000);
            }
        }
    }

    private void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();
        loggerLoad.info("Appium server started.");

        try {
            Thread.sleep(5000); // Wait 5 seconds for Appium server to fully initialize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


