package com.zerobank.utilities;

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver() {
    }
    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    public static WebDriver get() {
        //if this thread doesn't have driver - create it and add to pool
        if (driverPool.get() == null) {
//            if we pass the driver from terminal then use that one
//           if we do not pass the driver from terminal then use the one properties file
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : com.zerobank.utilities.ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "chromeSSL":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions capability = new ChromeOptions();
                    capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                    driverPool.set(new ChromeDriver(capability));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "remote-chromeSSL":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions remoteCapability = new ChromeOptions();
                    remoteCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    remoteCapability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    remoteCapability.setCapability("platform",Platform.ANY);
                    try{
                        driverPool.set(new RemoteWebDriver(new URL("http://18.212.6.39:4444/wd/hub"),remoteCapability));
                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("platform", Platform.ANY);
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("https://oauth-oscar-ced7a:b15c2e7a-0dfd-4efd-b0b6-a4e421b3a616@ondemand.eu-central-1.saucelabs.com:443/wd/hub"),chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_chrome":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                    desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"),desiredCapabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_mobile_chrome":
                    DesiredCapabilities desiredCapabilitiesR = new DesiredCapabilities();
                    desiredCapabilitiesR.setCapability("appiumVersion", "1.20.2");
                    desiredCapabilitiesR.setCapability("deviceName","Samsung Galaxy S8 FHD GoogleAPI Emulator");
                    desiredCapabilitiesR.setCapability("deviceOrientation", "portrait");
                    desiredCapabilitiesR.setCapability("browserName", "");
                    desiredCapabilitiesR.setCapability("platformVersion","8.0");
                    desiredCapabilitiesR.setCapability("platformName","Android");
                    desiredCapabilitiesR.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("https://oauth-oscar-ced7a:b15c2e7a-0dfd-4efd-b0b6-a4e421b3a616@ondemand.eu-central-1.saucelabs.com:443/wd/hub"),desiredCapabilitiesR));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;


            }
        }
        return driverPool.get();


    }
    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
}