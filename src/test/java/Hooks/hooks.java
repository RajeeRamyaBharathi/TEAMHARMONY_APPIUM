package Hooks;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import Utilities.ConfigReader;
import Utilities.drivermanager;
import Utilities.loggerLoad;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class hooks {
	
	AndroidDriver driver;
	AppiumDriverLocalService service;
	Process emulatorProcess;
	
	@Before
	 public void setUp() throws Exception {
		
		ConfigReader config = new ConfigReader();
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
        initializeDriver(deviceId, config);
	}
	
        private void initializeDriver(String deviceId, ConfigReader config) throws Exception {
            String appPath = System.getProperty("user.dir") + config.applocation();
            UiAutomator2Options options = new UiAutomator2Options()
                    .setAppWaitActivity("*")
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setDeviceName(config.emulatorName())
                    .setUdid(deviceId)
                    .setAppPackage(config.appPackage())
                    .setAppActivity(config.appActivity())
                    .setApp(appPath);
            driver= new AndroidDriver(new URI(config.appiumserver()).toURL(), options);
            drivermanager.setdriver(driver);

            loggerLoad.info("Driver initialized and connected.");
        }
	
	@After
         public void teardown(Scenario scenario) {
		 driver = drivermanager.getdriver();
        
        	try {
        		if (driver != null && scenario.isFailed()) {
        		takeScreenshot(scenario, driver);
        		}
        		
            } catch (Exception e) {
                loggerLoad.error("❌ Screenshot failed: " + e.getMessage());
            }
        	
        	try {
        	if (driver != null) {
                sendTestInfoToAppium(scenario, driver);
            } 
        	} catch (Exception e) {
                loggerLoad.error("❌ Sending test info failed: " + e.getMessage());
            }
        	
        	try {
            	if(driver!=null) {
                 driver.quit();
                 drivermanager.unload();
                 loggerLoad.info("Driver quit.");
            }
        	} catch (Exception e) {
                loggerLoad.error("❌ Driver quit failed: " + e.getMessage());
            }
            	 // ✅ Fetch and save Appium HTML report
                try {
                	if(driver!=null) {
                    URL appiumServerUrl = driver.getRemoteAddress();
                    URL getReportUrl = new URL(appiumServerUrl, "/getReport");

                    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) getReportUrl.openConnection();
                    conn.setRequestMethod("GET");

                    if (conn.getResponseCode() == 200) {
                        StringBuilder reportHtml = new StringBuilder();
                        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                                new java.io.InputStreamReader(conn.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                reportHtml.append(line).append(System.lineSeparator());
                            }
                        }
                        
                        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                        String status = scenario.isFailed() ? "failed" : "passed";
                        File reportDir = new File("target/appium-reports");
                        if (!reportDir.exists()) reportDir.mkdirs();
                        File reportFile = new File(reportDir, scenarioName + "_" + timestamp + ".html");
                        //File reportFile = new File(reportDir, "report.html");
                        Files.write(reportFile.toPath(), reportHtml.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));

                        loggerLoad.info(" Appium HTML report saved to: " + reportFile.getAbsolutePath());
                    } else {
                        loggerLoad.warn("Appium Reporter Plugin: Failed to fetch HTML report. HTTP code: " + conn.getResponseCode());
                    }
                } 
                }catch (Exception e) {
                    loggerLoad.error(" Failed to fetch or save Appium HTML report: " + e.getMessage());
                }

        
        
        if (service != null && service.isRunning()) {
            service.stop();
            loggerLoad.info("Appium server stopped.");
        }
        if (emulatorProcess != null) {
            emulatorProcess.destroy();
            loggerLoad.info("Emulator process killed.");
        }
            }
	
	private void takeScreenshot(Scenario scenario, AndroidDriver driver) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String status = scenario.isFailed() ? "failed" : "passed";

        String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        File destDir = new File("target/screenshots/" + status);
        if (!destDir.exists()) destDir.mkdirs();

        File destFile = new File(destDir, fileName);
        Files.copy(screenshot.toPath(), destFile.toPath());

        loggerLoad.info("Screenshot saved at: " + destFile.getAbsolutePath());

        byte[] bytes = java.nio.file.Files.readAllBytes(destFile.toPath());
        scenario.attach(bytes, "image/png", "Screenshot - " + status);
    }
	
	 private void sendTestInfoToAppium(Scenario scenario, AndroidDriver driver) {
	        try {
	            String sessionId = driver.getSessionId().toString();
	            String testName = scenario.getName();
	            String testStatus = scenario.isFailed() ? "FAILED" : "PASSED";
	            String error = scenario.isFailed() ? scenario.getStatus().toString() : "";

	            String jsonPayload = String.format(
	                    "{\"sessionId\":\"%s\", \"testName\":\"%s\", \"testStatus\":\"%s\", \"error\":\"%s\"}",
	                    sessionId, testName, testStatus, error);
	            java.net.URL appiumServerUrl = driver.getRemoteAddress();
	            java.net.URL url = new java.net.URL(appiumServerUrl, "/setTestInfo");
	            java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Content-Type", "application/json");
	            con.setDoOutput(true);
	            try (java.io.OutputStream os = con.getOutputStream()) {
	                byte[] input = jsonPayload.getBytes("utf-8");
	                os.write(input, 0, input.length);
	            }
	            int code = con.getResponseCode();
	            loggerLoad.info("Appium Reporter Plugin: setTestInfo HTTP response code: " + code);
	        } catch (Exception e) {
	            loggerLoad.error("Failed to send test info to Appium server: " + e.getMessage());
	        }
	    }

	private boolean isEmulatorRunning(String deviceId) throws IOException {
		 Process process = new ProcessBuilder("adb", "devices").start();
	        String output = new String(process.getInputStream().readAllBytes());
	        return output.contains(deviceId);
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
        int attempts = 0;
        int maxAttempts = 30; // wait max 1 minute
        while (!booted && attempts < maxAttempts) {
            Process process = new ProcessBuilder(command).start();
            String result = new String(process.getInputStream().readAllBytes()).trim();
            if ("1".equals(result)) {
                booted = true;
                loggerLoad.info("Emulator boot completed.");
            } else {
                Thread.sleep(2000);
                attempts++;
            }
        }
        if (!booted) {
            throw new RuntimeException("Emulator did not boot within expected time.");
        }
    }

    private void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withArgument(() -> "--use-plugins", "appium-reporter-plugin,element-wait") // ✅ Enable plugin
                .withEnvironment(Map.of("APPIUM_REPORTER_CONFIG", "reporter.config.json"))
                .build();
        service.start();
        loggerLoad.info("Appium server started.");
        int retries = 10;
        boolean started = false;
        while (retries > 0) {
            if (service.isRunning()) {
                started = true;
                break;
            }
        try {
            Thread.sleep(1000); // Wait 5 seconds for Appium server to fully initialize
        } catch (InterruptedException ignored) {
        }
        retries--;
    }
        if (!started) {
            throw new RuntimeException("Appium server failed to start.");
        }
    }
}


