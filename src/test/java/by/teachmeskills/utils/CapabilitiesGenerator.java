package by.teachmeskills.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class CapabilitiesGenerator {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String driverPath = "src/test/resources/webdrivers";
        String os = System.getProperty("os.name").toLowerCase();
        log.debug(String.format("Operational system: %s; Driver path: %s", os, driverPath));
        if (os.contains("win")) {
            log.debug(String.format("SetProperty: webdriver.chrome.driver with %s/chromedriver.exe", driverPath));
            System.setProperty("webdriver.chrome.driver", driverPath + "/chromedriver.exe");
        } else if (os.contains("mac")) {
            log.debug(String.format("SetProperty: webdriver.chrome.driver with %s/chromedriver", driverPath));
            System.setProperty("webdriver.chrome.driver", driverPath + "/chromedriver");
        } else {
            log.debug(String.format("SetProperty: webdriver.chrome.driver with %s/linux/chromedriver", driverPath));
            System.setProperty("webdriver.chrome.driver", driverPath + "/linux/chromedriver");
        }
        log.debug(String.format("ChromeOptions --ignore-certificate-errors"));
        options.addArguments("--ignore-certificate-errors");
        log.debug(String.format("ChromeOptions --disable-popup-blocking"));
        options.addArguments("--disable-popup-blocking");
        log.debug(String.format("ChromeOptions --disable-notifications"));
        options.addArguments("--disable-notifications");
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            log.debug(String.format("ChromeOptions --headless"));
            options.addArguments("--headless");
        }
        // only if you are ACTUALLY running headless
        //options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        //options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        //options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        //options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        //options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc

        return options;
    }
}
